package com.sw.wordgarden.presentation.ui.login.onboarding

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.sw.wordgarden.R
import com.sw.wordgarden.databinding.FragmentOnboardingBinding
import com.sw.wordgarden.domain.entity.user.LoginRequestEntity
import com.sw.wordgarden.presentation.event.DefaultEvent
import com.sw.wordgarden.presentation.ui.loading.LoadingDialog
import com.sw.wordgarden.presentation.util.Constants.NICKNAME_LIMIT
import com.sw.wordgarden.presentation.util.ImageConverter.uriToString
import com.sw.wordgarden.presentation.util.ToastMaker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class OnBoardingFragment : Fragment() {
    private val TAG = "OnBoardingFragment"

    private var _binding: FragmentOnboardingBinding? = null
    private val binding get() = _binding!!

    private var loadingDialog: LoadingDialog? = null
    private val viewmodel: OnBoardingViewModel by viewModels()
    private val galleryLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) {
        it?.let {
            binding.ivOnboardingThumbnail.setImageURI(it)
            thumbnail = uriToString(it, requireContext())
        }
    }
    private lateinit var loginRequestEntity: LoginRequestEntity
    private var thumbnail: String? = null
    private var nickname: String? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnboardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getDataFromLogin()
        setupUi()
        setupListener()
        setupObserver()
    }

    private fun getDataFromLogin() {
        val args: OnBoardingFragmentArgs by navArgs()
        loginRequestEntity = args.argsLoginRequestEntity ?: LoginRequestEntity("", "", "", "")
    }

    private fun setupUi() = with(binding) {
        val inputFilter = InputFilter { source, start, end, dest, dstart, dend ->
            val regex = Regex("[가-힣a-zA-Z0-9]+")
            val filtered = source.filter { it.toString().matches(regex) }

            if (filtered == source) {
                null
            } else {
                filtered
            }
        }
        etOnboardingNickname.filters = arrayOf(inputFilter, InputFilter.LengthFilter(10))
    }

    @SuppressLint("SetTextI18n")
    private fun setupListener() = with(binding) {
        ivOnboardingCamera.setOnClickListener {
            galleryLauncher.launch("image/*")
        }
        ivOnboardingThumbnail.setOnClickListener {
            galleryLauncher.launch("image/*")
        }

        etOnboardingNickname.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val inputLength = s?.length ?: 0
                tvOnboardingLimit.text = "$inputLength/$NICKNAME_LIMIT"
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        ivOnboardingNext.setOnClickListener {
            nickname = binding.etOnboardingNickname.text.toString()

            if (nickname == "") {
                ToastMaker.make(requireContext(), getString(R.string.onboarding_msg_fill))
            } else {
                loginRequestEntity = loginRequestEntity.copy(
                    uid = loginRequestEntity.uid,
                    nickname = nickname ?: "",
                    provider = loginRequestEntity.provider,
                    fcmToken = loginRequestEntity.fcmToken
                )

                viewmodel.deleteUidForStartingSignUp()
            }
        }
    }

    private fun setupObserver() {
        lifecycleScope.launch {
            viewmodel.deleteUidEvent.flowWithLifecycle(lifecycle).collectLatest { event ->
                when (event) {
                    is DefaultEvent.Failure -> {
                        ToastMaker.make(requireContext(), event.msg)
                    }

                    DefaultEvent.Success -> {
                        Log.i(TAG, "success delete local UID")
                        viewmodel.saveUid(loginRequestEntity.uid)
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
                        viewmodel.signUp(loginRequestEntity)
                    }
                }
            }
        }

        lifecycleScope.launch {
            viewmodel.insertEvent.flowWithLifecycle(lifecycle).collectLatest { event ->
                when (event) {
                    is DefaultEvent.Failure -> {
                        ToastMaker.make(requireContext(), event.msg)
                    }

                    DefaultEvent.Success -> {
                        Log.i(TAG, "success insert user")

                        if (!thumbnail.isNullOrBlank()) { //썸네일 이미지가 있으면 추가 요청
                            viewmodel.updateImage(thumbnail!!)
                        } else {
                            goHome()
                        }
                    }
                }
            }
        }

        lifecycleScope.launch {
            viewmodel.updateEvent.flowWithLifecycle(lifecycle).collectLatest { event ->
                when (event) {
                    is DefaultEvent.Failure -> {
                        ToastMaker.make(requireContext(), event.msg)
                    }

                    DefaultEvent.Success -> {
                        Log.i(TAG, "success update user image")
                        goHome()
                    }
                }
            }
        }

        lifecycleScope.launch {
            viewmodel.uiState.flowWithLifecycle(lifecycle).collectLatest { state ->
                if (state.isLoading) {
                    loadingDialog = LoadingDialog()
                    loadingDialog?.show(parentFragmentManager, null)
                } else {
                    loadingDialog?.dismiss()
                    loadingDialog = null
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