package com.sw.wordgarden.presentation.ui.mypage.mypage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
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
            findNavController().popBackStack()
        }
        ivMySetting.setOnClickListener {
            findNavController().navigate(R.id.action_mypageFragment_to_settingFragment)
        }
        ivMyEdit.setOnClickListener {

        }
        ivMyWeeklyScoreMore.setOnClickListener {
            findNavController().navigate(R.id.action_mypageFragment_to_weeklyQuizCheckFragment)
        }
        ivMyMadeQuizMore.setOnClickListener {
            findNavController().navigate(R.id.action_mypageFragment_to_myMadeQuizFragment)
        }
        ivMySolvedQuizMore.setOnClickListener {
            findNavController().navigate(R.id.action_mypageFragment_to_mySolvedQuizFragment)
        }
        ivMyTakenQuizMore.setOnClickListener {
            findNavController().navigate(R.id.action_mypageFragment_to_myTakenQuizFragment)
        }
        ivMyFriendsMore.setOnClickListener {
            findNavController().navigate(R.id.action_mypageFragment_to_friendsFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}