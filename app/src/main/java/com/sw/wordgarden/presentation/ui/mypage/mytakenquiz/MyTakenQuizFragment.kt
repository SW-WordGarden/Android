package com.sw.wordgarden.presentation.ui.mypage.mytakenquiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sw.wordgarden.databinding.FragmentMyTakenQuizBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyTakenQuizFragment : Fragment() {

    private var _binding: FragmentMyTakenQuizBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMyTakenQuizBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}