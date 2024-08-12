package com.sw.wordgarden.presentation.ui.mypage.mypage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sw.wordgarden.R
import com.sw.wordgarden.databinding.FragmentMypageBinding
import com.sw.wordgarden.presentation.ui.mypage.friends.FriendsFragment
import com.sw.wordgarden.presentation.ui.mypage.mymadequiz.MyMadeQuizFragment
import com.sw.wordgarden.presentation.ui.mypage.mysolvedquiz.MySolvedQuizFragment
import com.sw.wordgarden.presentation.ui.mypage.mytakenquiz.MyTakenQuizFragment
import com.sw.wordgarden.presentation.ui.mypage.weeklyquizcheck.WeeklyQuizCheckFragment
import com.sw.wordgarden.presentation.ui.setting.SettingFragment
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
        ivMySetting.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.cl_my_main, SettingFragment())
                .addToBackStack(null)
                .commit()
        }
        ivMyEdit.setOnClickListener {

        }
        ivMyWeeklyScoreMore.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.cl_my_main, WeeklyQuizCheckFragment())
                .addToBackStack(null)
                .commit()
        }
        ivMyMadeQuizMore.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.cl_my_main, MyMadeQuizFragment())
                .addToBackStack(null)
                .commit()
        }
        ivMySolvedQuizMore.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.cl_my_main, MySolvedQuizFragment())
                .addToBackStack(null)
                .commit()
        }
        ivMyTakenQuizMore.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.cl_my_main, MyTakenQuizFragment())
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