package com.sw.wordgarden.presentation.ui.login.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
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
import com.sw.wordgarden.domain.entity.user.LoginRequestEntity
import com.sw.wordgarden.presentation.event.DefaultEvent
import com.sw.wordgarden.presentation.event.UserCheckEvent
import com.sw.wordgarden.presentation.ui.main.MainViewModel
import com.sw.wordgarden.presentation.util.Constants.TESTER_PROVIDER
import com.sw.wordgarden.presentation.util.Constants.TESTER_UID
import com.sw.wordgarden.presentation.util.ToastMaker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private val TAG = "LoginFragment"

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val viewmodel: LoginViewModel by viewModels()
    private val mainViewmodel: MainViewModel by activityViewModels()
    private var uid = ""
    private var provider = ""
    private var token = ""
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
        setupObserver()
    }

    private fun setupListener() = with(binding) {
        llLoginNaver.setOnClickListener { naverLogin() }
        llLoginKakao.setOnClickListener { kakaoLogin() }
        tvLoginId.setOnClickListener { idLogin() }
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
                Log.e(
                    TAG,
                    "fail to naver login - errorCode:$errorCode, errorDesc:$errorDescription"
                )
            }

            override fun onSuccess() {
                Log.i(TAG, "success naver login ${NaverIdLoginSDK.getAccessToken()}")

                NidOAuthLogin().callProfileApi(object : NidProfileCallback<NidProfileResponse> {
                    override fun onSuccess(result: NidProfileResponse) {
                        Log.i(
                            TAG,
                            "success request for naver user info - user number: ${result.profile?.id}"
                        )

                        provider = NAVER

                        checkMember(result.profile?.id ?: "")
                    }

                    override fun onFailure(httpStatus: Int, message: String) {
                        val errorCode = NaverIdLoginSDK.getLastErrorCode().code
                        val errorDescription = NaverIdLoginSDK.getLastErrorDescription()
                        Log.e(
                            TAG,
                            "fail to request for naver user info - errorCode:$errorCode, errorDesc:$errorDescription"
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
                Log.e(TAG, "fail to kakao login", error)
            } else if (token != null) {
                Log.i(TAG, "success kakao login ${token.accessToken}")

                provider = KAKAO

                UserApiClient.instance.me { user, e ->
                    if (e != null) {
                        Log.e(TAG, "fail to request for kakao user info", e)
                    } else if (user != null) {
                        Log.i(TAG, "success request for kakao user info - user number: ${user.id}")
                        checkMember(user.id.toString())
                    }
                }
            }
        }

        if (UserApiClient.instance.isKakaoTalkLoginAvailable(requireContext())) {
            UserApiClient.instance.loginWithKakaoTalk(requireContext()) { token, error ->
                if (error != null) {
                    Log.e(TAG, "fail to kakao login $error")

                    if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                        return@loginWithKakaoTalk
                    }

                    UserApiClient.instance.loginWithKakaoAccount(
                        requireContext(),
                        callback = callback
                    )
                } else if (token != null) {
                    Log.i(TAG, "success kakao login ${token.accessToken}")

                    provider = KAKAO

                    UserApiClient.instance.me { user, e ->
                        if (e != null) {
                            Log.e(TAG, "fail to request for kakao user info", e)
                        } else if (user != null) {
                            Log.i(
                                TAG,
                                "success request for kakao user info - user number: ${user.id}"
                            )
                            checkMember(user.id.toString())
                        }
                    }
                }
            }
        } else {
            UserApiClient.instance.loginWithKakaoAccount(requireContext(), callback = callback)
        }
    }

    private fun idLogin() {
        provider = TESTER_PROVIDER
        checkMember(TESTER_UID)
    }

    private fun checkMember(uid: String) {
        this.uid = uid
        viewmodel.deleteUidForStartingLogin()
    }

    private fun setupObserver() {
        lifecycleScope.launch {
            viewmodel.getUidEvent.flowWithLifecycle(lifecycle).collectLatest { event ->
                when (event) {
                    is DefaultEvent.Failure -> {
                        ToastMaker.make(requireContext(), event.msg)
                    }

                    DefaultEvent.Success -> {}
                }
            }
        }

        lifecycleScope.launch {
            viewmodel.getUid.flowWithLifecycle(lifecycle).collectLatest { uid ->
                if (!uid.isNullOrEmpty() && uid.isNotBlank()) {
                    goHome()
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
                        Log.i(TAG, "success delete local UID")
                        viewmodel.checkUserInfo(uid)
                    }
                }
            }
        }

        lifecycleScope.launch {
            viewmodel.checkUserEvent.flowWithLifecycle(lifecycle).collectLatest { event ->
                when (event) {
                    is UserCheckEvent.Failure -> {
                        ToastMaker.make(requireContext(), event.msg)
                    }

                    is UserCheckEvent.NotFound -> {
                        goOnboarding()
                    }

                    UserCheckEvent.Success -> {
                        viewmodel.saveUid(uid)
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
                        Log.i(TAG, "success save local UID")

                        viewmodel.updateToken(token)
                    }
                }
            }
        }

        lifecycleScope.launch {
            viewmodel.updateTokenEvent.flowWithLifecycle(lifecycle).collectLatest { event ->
                when (event) {
                    is DefaultEvent.Failure -> {
                        ToastMaker.make(requireContext(), event.msg)
                        Log.i(TAG, "fail update fcm token")
                    }

                    DefaultEvent.Success -> {
                        Log.i(TAG, "success update fcm token")
                    }
                }
                goHome()
            }
        }

        lifecycleScope.launch {
            mainViewmodel.fcmToken.flowWithLifecycle(lifecycle).collectLatest { token ->
                this@LoginFragment.token = token ?: ""
            }
        }
    }

    private fun goHome() {
        findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
    }

    private fun goOnboarding() {
        val loginRequestEntity = LoginRequestEntity(
            uid = uid,
            nickname = "",
            provider = provider,
            fcmToken = token
        )

        findNavController().apply {
            if (currentBackStackEntry?.destination?.id == R.id.onBoardingFragment) {
                popBackStack(R.id.onBoardingFragment, false)
            } else {
                val action = LoginFragmentDirections.actionLoginFragmentToOnBoardingFragment(
                    loginRequestEntity
                )
                navigate(action)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}