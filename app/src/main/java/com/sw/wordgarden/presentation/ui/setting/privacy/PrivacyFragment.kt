package com.sw.wordgarden.presentation.ui.setting.privacy

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.sw.wordgarden.R
import com.sw.wordgarden.databinding.FragmentPrivacyBinding
import com.sw.wordgarden.presentation.util.Constants.PRIVACY_RULE
import dagger.hilt.android.AndroidEntryPoint
import java.io.FileNotFoundException

@AndroidEntryPoint
class PrivacyFragment : Fragment() {

    private var _binding: FragmentPrivacyBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPrivacyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUi()
        setupListener()
    }

    private fun setupUi() {
        try {
            val assetManager = this.requireContext().assets.open(PRIVACY_RULE)
            val htmlText = assetManager.bufferedReader().use { it.readText() }
            val spannedText = HtmlCompat.fromHtml(htmlText, HtmlCompat.FROM_HTML_MODE_LEGACY)
            binding.tvSettingPrivacyContents.text = spannedText
        } catch (e: FileNotFoundException) {
            Log.e("Privacy", "file not found")
            binding.tvSettingPrivacyContents.text = getString(R.string.setting_msg_file_error)
        }
    }

    private fun setupListener() = with(binding) {
        ivSettingAgreementBack.setOnClickListener {
            ivSettingAgreementBack.isEnabled = false
            goBack()
        }
    }

    private fun goBack() {
        val navController = findNavController()
        val currentDestination = navController.currentDestination?.id

        if (currentDestination == R.id.privacyFragment) {
            findNavController().navigateUp()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}