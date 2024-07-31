package com.sw.wordgarden.presentation.ui.quiz.sharequiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sw.wordgarden.databinding.FragmentShareQuizBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShareQuizFragment : Fragment() {

    private var _binding: FragmentShareQuizBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShareQuizBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}