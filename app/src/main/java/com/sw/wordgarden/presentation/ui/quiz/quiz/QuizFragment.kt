package com.sw.wordgarden.presentation.ui.quiz.quiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sw.wordgarden.R
import com.sw.wordgarden.databinding.FragmentQuizBinding
import com.sw.wordgarden.presentation.ui.quiz.makequiz.MakeQuizFragment
import com.sw.wordgarden.presentation.ui.quiz.sharequiz.ShareQuizFragment
import com.sw.wordgarden.presentation.ui.quiz.solvequiz.SolveQuizFragment
import com.sw.wordgarden.presentation.ui.quiz.startquiz.StartQuizFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuizFragment : Fragment() {

    private var _binding: FragmentQuizBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQuizBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUi()
        setupListener()
    }

    private fun setupUi() = with(binding) {
//        ivQuizUserThumbnail.setImageResource()
//        tvQuizUserName.text = ""
    }

    private fun setupListener() = with(binding) {
        btnQuizBack.setOnClickListener {
            //findNavController().popBackStack()
        }
        btnQuizAlone.setOnClickListener {
            //findNavController().navigate(해당 화면)

            /**
             * test용 이동 코드
             */
            parentFragmentManager.beginTransaction()
                .replace(R.id.cl_quiz_main, StartQuizFragment())
                .addToBackStack(null)
                .commit()
        }
        btnQuizFriend.setOnClickListener {
            //findNavController().navigate(해당 화면)

            /**
             * test용 이동 코드
             */
            parentFragmentManager.beginTransaction()
                .replace(R.id.cl_quiz_main, ShareQuizFragment())
                .addToBackStack(null)
                .commit()
        }
        btnMakeQuiz.setOnClickListener {
            //findNavController().navigate(해당 화면)

            /**
             * test용 이동 코드
             */
            parentFragmentManager.beginTransaction()
                .replace(R.id.cl_quiz_main, MakeQuizFragment())
                .addToBackStack(null)
                .commit()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}