package com.sw.wordgarden.presentation.ui.login.onboarding

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
import androidx.navigation.fragment.navArgs
import com.sw.wordgarden.R
import com.sw.wordgarden.databinding.FragmentOnboardingBinding
import com.sw.wordgarden.domain.entity.user.LoginRequestEntity
import com.sw.wordgarden.presentation.event.DefaultEvent
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
    private lateinit var loginRequestEntity: LoginRequestEntity

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
        val args: OnBoardingFragmentArgs by navArgs()
        loginRequestEntity = args.argsSignUpEntity ?: LoginRequestEntity("", "", "")
    }

    private fun setupListener() {
        binding.btnOnboardingNext.setOnClickListener {
            val nickname = binding.etOnboardingNickname.text.toString()

            if (nickname == "") {
                ToastMaker.make(requireContext(), getString(R.string.onboarding_msg_fill))
            } else {
                val signUpData = loginRequestEntity.copy(
                    uid = loginRequestEntity.uid,
                    nickname = nickname,
                    provider = loginRequestEntity.provider
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
        findNavController().navigate(R.id.action_onBoardingFragment_to_homeFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}