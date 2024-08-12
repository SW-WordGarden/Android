package com.sw.wordgarden.presentation.ui.mypage.mysolvedquiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.sw.wordgarden.databinding.FragmentMySolvedQuizBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MySolvedQuizFragment : Fragment() {

    private var _binding: FragmentMySolvedQuizBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMySolvedQuizBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListener()
    }

    private fun setupListener() = with(binding) {
        ivMySolvedQuizBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}