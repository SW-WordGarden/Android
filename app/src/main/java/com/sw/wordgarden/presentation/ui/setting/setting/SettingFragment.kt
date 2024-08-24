package com.sw.wordgarden.presentation.ui.setting.setting

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.sw.wordgarden.R
import com.sw.wordgarden.databinding.FragmentSettingBinding
import com.sw.wordgarden.presentation.event.DefaultEvent
import com.sw.wordgarden.presentation.util.ToastMaker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SettingFragment : Fragment() {

    private var _binding: FragmentSettingBinding? = null
    private val binding get() = _binding!!

    private val viewmodel: SettingViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListener()
        setupObserver()
    }

    private fun setupListener() = with(binding) {
        ivSettingBack.setOnClickListener {
            findNavController().navigate(R.id.action_settingFragment_to_mypageFragment)
        }
        tvSettingAgreement.setOnClickListener {
            findNavController().navigate(R.id.action_settingFragment_to_agreementFragment)
        }
        tvSettingLogout.setOnClickListener {
            logout()
        }
    }

    private fun logout() {
        val builder = AlertDialog.Builder(requireActivity())
        builder.setMessage(R.string.setting_msg_logout)
        builder.setPositiveButton(R.string.setting_msg_logout_yes) { _, _ ->
            viewmodel.deleteUidForLogout()
        }
        builder.setNegativeButton(R.string.setting_msg_logout_no) { _, _ -> }
        builder.show()


    }

    private fun setupObserver() {
        lifecycleScope.launch {
            viewmodel.deleteUidEvent.flowWithLifecycle(lifecycle).collectLatest { event ->
                when (event) {
                    is DefaultEvent.Failure -> {
                        ToastMaker.make(requireContext(), event.msg)
                    }

                    DefaultEvent.Success -> {
                        goLogin()
                    }
                }
            }
        }
    }

    private fun goLogin() {
        findNavController().navigate(R.id.action_settingFragment_to_loginFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}