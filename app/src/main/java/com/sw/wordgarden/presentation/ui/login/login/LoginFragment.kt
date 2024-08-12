package com.sw.wordgarden.presentation.ui.login.login

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import com.navercorp.nid.NaverIdLoginSDK
import com.navercorp.nid.oauth.NidOAuthLogin
import com.navercorp.nid.oauth.OAuthLoginCallback
import com.navercorp.nid.profile.NidProfileCallback
import com.navercorp.nid.profile.data.NidProfileResponse
import com.sw.wordgarden.R
import com.sw.wordgarden.databinding.FragmentLoginBinding
import com.sw.wordgarden.domain.entity.SignUpEntity
import com.sw.wordgarden.presentation.model.DefaultEvent
import com.sw.wordgarden.presentation.model.UserCheckEvent
import com.sw.wordgarden.presentation.util.ToastMaker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private val TAG = "LoginFragment"

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val viewmodel: LoginViewModel by activityViewModels()
    private var uid = ""
    private var provider = ""
    private val NAVER = "NAVER"
    private val KAKAO = "KAKAO"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListener()
        setViewModelEvent()
    }

    private fun setupListener() {
        binding.llLoginNaver.setOnClickListener { naverLogin() }
        binding.llLoginKakao.setOnClickListener { kakaoLogin() }
    }

    private fun naverLogin() {
        NaverIdLoginSDK.initialize(
            requireContext(),
            getString(R.string.naver_client_id),
            getString(R.string.naver_client_secret),
            getString(R.string.app_name)
        )

        val oauthLoginCallback = object : OAuthLoginCallback {
            override fun onError(errorCode: Int, message: String) {
                onFailure(errorCode, message)
            }

            override fun onFailure(httpStatus: Int, message: String) {
                val errorCode = NaverIdLoginSDK.getLastErrorCode().code
                val errorDescription = NaverIdLoginSDK.getLastErrorDescription()
                Log.e(TAG, "네이버 로그인 실패 errorCode:$errorCode, errorDesc:$errorDescription")
            }

            override fun onSuccess() {
                Log.i(TAG, "네이버 로그인 성공 ${NaverIdLoginSDK.getAccessToken()}")

                NidOAuthLogin().callProfileApi(object : NidProfileCallback<NidProfileResponse> {
                    override fun onSuccess(result: NidProfileResponse) {
                        Log.i(TAG, "네이버 사용자 정보 요청 성공 - 회원번호: ${result.profile?.id}")

                        provider = NAVER

                        checkMember(result.profile?.id ?: "")
                    }

                    override fun onFailure(httpStatus: Int, message: String) {
                        val errorCode = NaverIdLoginSDK.getLastErrorCode().code
                        val errorDescription = NaverIdLoginSDK.getLastErrorDescription()
                        Log.e(
                            TAG,
                            "네이버 사용자 정보 요청 실패 errorCode:$errorCode, errorDesc:$errorDescription"
                        )

                    }

                    override fun onError(errorCode: Int, message: String) {
                        onFailure(errorCode, message)
                    }
                })
            }
        }

        NaverIdLoginSDK.authenticate(requireContext(), oauthLoginCallback)
    }

    private fun kakaoLogin() {
        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if (error != null) {
                Log.e(TAG, "카카오계정으로 로그인 실패", error)
            } else if (token != null) {
                Log.i(TAG, "카카오계정으로 로그인 성공 ${token.accessToken}")

                provider = KAKAO

                UserApiClient.instance.me { user, e ->
                    if (e != null) {
                        Log.e(TAG, "카카오 사용자 정보 요청 실패", e)
                    } else if (user != null) {
                        Log.i(TAG, "카카오 사용자 정보 요청 성공 - 회원번호: ${user.id}")
                        checkMember(user.id.toString())
                    }
                }
            }
        }

        //카카오톡 설치 시 카카오톡으로 로그인, 아니면 카카오계정으로 로그인
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(requireContext())) {
            UserApiClient.instance.loginWithKakaoTalk(requireContext()) { token, error ->
                if (error != null) {
                    Log.e(TAG, "카카오톡으로 로그인 실패 $error")

                    //사용자가 카카오톡 설치 후 디바이스 권한 요청 화면에서 로그인 취소한 경우, 취소 처리
                    if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                        return@loginWithKakaoTalk
                    }

                    //카카오톡에 연결된 카카오계정이 없는 경우, 카카오계정으로 로그인 시도
                    UserApiClient.instance.loginWithKakaoAccount(
                        requireContext(),
                        callback = callback
                    )
                } else if (token != null) {
                    Log.i(TAG, "카카오톡으로 로그인 성공 ${token.accessToken}")

                    provider = KAKAO

                    UserApiClient.instance.me { user, e ->
                        if (e != null) {
                            Log.e(TAG, "카카오 사용자 정보 요청 실패", e)
                        } else if (user != null) {
                            Log.i(TAG, "카카오 사용자 정보 요청 성공 - 회원번호: ${user.id}")
                            checkMember(user.id.toString())
                        }
                    }
                }
            }
        } else {
            UserApiClient.instance.loginWithKakaoAccount(requireContext(), callback = callback)
        }
    }

    private fun checkMember(uid: String) {

        this.uid = uid

        viewmodel.checkUserInfo(uid)
    }

    private fun setViewModelEvent() {
        lifecycleScope.launch {
            viewmodel.checkUserEvent.flowWithLifecycle(lifecycle).collectLatest { event ->
                when (event) {
                    is UserCheckEvent.Failure -> {
                        ToastMaker.make(requireContext(), event.msg)
                    }
                    is UserCheckEvent.NotFound -> { goOnboarding() }
                    UserCheckEvent.Success -> { goHome() }
                }
            }
        }

        lifecycleScope.launch {
            viewmodel.deleteUidEvent.flowWithLifecycle(lifecycle).collectLatest { event ->
                when (event) {
                    is DefaultEvent.Failure -> {
                        ToastMaker.make(requireContext(), event.msg)
                    }
                    DefaultEvent.Success -> {
                        Log.i(TAG, "기기에 저장된 UID 초기화 완료")
                    }
                }
            }
        }

        lifecycleScope.launch {
            viewmodel.saveUidEvent.flowWithLifecycle(lifecycle).collectLatest { event ->
                when (event) {
                    is DefaultEvent.Failure -> {
                        ToastMaker.make(requireContext(), event.msg)
                    }
                    DefaultEvent.Success -> {
                        Log.i(TAG, "UID 기기 저장 완료")
                    }
                }
            }
        }
    }

    private fun goHome() {
        findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
    }

    private fun goOnboarding() {
        val signUpEntity = SignUpEntity(
            uid = uid,
            nickname = "",
            provider = provider
        )

        val action = LoginFragmentDirections.actionLoginFragmentToOnBoardingFragment(signUpEntity)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}