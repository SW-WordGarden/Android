package com.sw.wordgarden.presentation.ui.mypage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sw.wordgarden.R
import com.sw.wordgarden.databinding.FragmentMypageBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MypageFragment : Fragment() {

    private var _binding: FragmentMypageBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMypageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListener()
    }

    private fun setupListener() = with(binding) {
        ivMyBack.setOnClickListener {

        }
        ivMyMenu.setOnClickListener {

        }
        ivMyEdit.setOnClickListener {

        }
        ivMyWeeklyScoreMore.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.cl_my_main, WeeklyFragment())
                .addToBackStack(null)
                .commit()
        }
        ivMyQuizzesMore.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.cl_my_main, MyQuizFragment())
                .addToBackStack(null)
                .commit()
        }
        ivMyFriendsMore.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.cl_my_main, FriendsFragment())
                .addToBackStack(null)
                .commit()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}