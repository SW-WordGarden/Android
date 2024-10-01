package com.sw.wordgarden.presentation.ui.setting.term

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.sw.wordgarden.R
import com.sw.wordgarden.databinding.FragmentTermBinding
import com.sw.wordgarden.presentation.util.Constants.USER_TERM
import dagger.hilt.android.AndroidEntryPoint
import java.io.FileNotFoundException

@AndroidEntryPoint
class TermFragment : Fragment() {

    private var _binding: FragmentTermBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTermBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUi()
        setupListener()
    }

    private fun setupUi() {
        try {
            val assetManager = this.requireContext().assets.open(USER_TERM)
            val htmlText = assetManager.bufferedReader().use { it.readText() }
            val spannedText = HtmlCompat.fromHtml(htmlText, HtmlCompat.FROM_HTML_MODE_LEGACY)
            binding.tvSettingTermContents.text = spannedText
        } catch (e: FileNotFoundException) {
            Log.e("Term", "file not found")
            binding.tvSettingTermContents.text = getString(R.string.setting_msg_file_error)
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

        if (currentDestination == R.id.termFragment) {
            findNavController().navigateUp()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}