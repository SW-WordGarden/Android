package com.sw.wordgarden.presentation.ui.login.onboarding

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.sw.wordgarden.R
import com.sw.wordgarden.databinding.FragmentOnboardingBinding
import com.sw.wordgarden.domain.entity.SignUpEntity
import com.sw.wordgarden.presentation.model.DefaultEvent
import com.sw.wordgarden.presentation.ui.home.HomeFragment
import com.sw.wordgarden.presentation.util.ToastMaker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class OnBoardingFragment : Fragment() {

    private val TAG = "OnBoardingFragment"

    private var _binding: FragmentOnboardingBinding? = null
    private val binding get() = _binding!!

    private val viewmodel: OnBoardingViewModel by activityViewModels()
    private lateinit var signUpEntity: SignUpEntity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        getDataFromLogin()

        _binding = FragmentOnboardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListener()
        setInsertEvent()
    }

    private fun getDataFromLogin() {
        setFragmentResultListener(LOGIN_TO_ONBOARDING_REQUEST_KEY) { _, bundle ->
            signUpEntity = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                bundle.getParcelable(LOGIN_TO_ONBOARDING_BUNDLE_KEY, SignUpEntity::class.java)
                    ?: SignUpEntity("", "", "")
            } else {
                bundle.getParcelable(LOGIN_TO_ONBOARDING_BUNDLE_KEY)
                    ?: SignUpEntity("", "", "")
            }
        }
    }

    private fun setupListener() {
        binding.btnOnboardingNext.setOnClickListener {
            val nickname = binding.etOnboardingNickname.text.toString()

            if (nickname == "") {
                ToastMaker.make(requireContext(), getString(R.string.onboarding_msg_fill))
            } else {
                val signUpData = signUpEntity.copy(
                    uid = signUpEntity.uid,
                    nickname = nickname,
                    provider = signUpEntity.provider
                )

                viewmodel.signUp(signUpData)
            }
        }
    }

    private fun setInsertEvent() {
        lifecycleScope.launch {
            viewmodel.insertEvent.flowWithLifecycle(lifecycle).collectLatest { event ->
                when (event) {
                    is DefaultEvent.Failure -> {
                        ToastMaker.make(requireContext(), event.msg)
                    }

                    DefaultEvent.Success -> {
                        Log.i(TAG, "회원가입 성공")
                        goHome()
                    }
                }
            }
        }
    }

    private fun goHome() {
        parentFragmentManager.beginTransaction()
            .replace(R.id.cl_login_main, HomeFragment())
            .addToBackStack(null)
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val LOGIN_TO_ONBOARDING_REQUEST_KEY = "LOGIN_TO_ONBOARDING_REQUEST_KEY"
        const val LOGIN_TO_ONBOARDING_BUNDLE_KEY = "LOGIN_TO_ONBOARDING_BUNDLE_KEY"
    }
}