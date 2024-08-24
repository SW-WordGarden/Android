package com.sw.wordgarden.presentation.ui.quiz.resultquiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.sw.wordgarden.R
import com.sw.wordgarden.databinding.FragmentResultQuestionOXBinding
import com.sw.wordgarden.presentation.util.Constants
import com.sw.wordgarden.presentation.util.Constants.QUESTION_O
import com.sw.wordgarden.presentation.util.Constants.QUESTION_OX_TITLE

class ResultQuestionOXFragment : Fragment() {

    private var _binding: FragmentResultQuestionOXBinding? = null
    private val binding get() = _binding!!

    private lateinit var question: String
    private lateinit var answer: String
    private lateinit var userAnswer: String
    private var position: Int = 0

    private var onNextClicked: ((position: Int, question: String, answer: String, userAnswer: String, isCorrect: Boolean) -> Unit)? =
        null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            question = it.getString(QUESTION_KEY).toString()
            answer = it.getString(ANSWER_KEY).toString()
            userAnswer = it.getString(USER_ANSWER_KEY).toString()
            position = it.getInt(POSITION_KEY)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResultQuestionOXBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUi()
    }

    private fun setupUi() = with(binding) {
        val question = question.substringAfter(QUESTION_OX_TITLE)
        tvResultQuizItemQuestionOxQuestion.text = question

        if (userAnswer == answer) {
            if (answer == QUESTION_O) {
                btnResultQuizItemQuestionO.backgroundTintList =
                    ContextCompat.getColorStateList(requireContext(), R.color.light_green)
            } else {
                btnResultQuizItemQuestionX.backgroundTintList =
                    ContextCompat.getColorStateList(requireContext(), R.color.light_green)
            }
        } else {
            if (answer == QUESTION_O) {
                btnResultQuizItemQuestionO.backgroundTintList =
                    ContextCompat.getColorStateList(requireContext(), R.color.light_green)
                btnResultQuizItemQuestionX.backgroundTintList =
                    ContextCompat.getColorStateList(requireContext(), R.color.light_pink)
            } else {
                btnResultQuizItemQuestionO.backgroundTintList =
                    ContextCompat.getColorStateList(requireContext(), R.color.light_pink)
                btnResultQuizItemQuestionX.backgroundTintList =
                    ContextCompat.getColorStateList(requireContext(), R.color.light_green)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        binding.root.requestLayout()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun setOnNextClickedListener(listener: (Int, String, String, String, Boolean) -> Unit) {
        onNextClicked = listener
    }

    companion object {
        const val QUESTION_KEY = "QUESTION_KEY"
        const val ANSWER_KEY = "ANSWER_KEY"
        const val USER_ANSWER_KEY = "USER_ANSWER_KEY"
        const val POSITION_KEY = "POSITION_KEY"

        fun newInstance(
            question: String,
            answer: String,
            userAnswer: String,
            position: Int
        ) =
            ResultQuestionOXFragment().apply {
                arguments = Bundle().apply {
                    putString(QUESTION_KEY, question)
                    putString(ANSWER_KEY, answer)
                    putString(USER_ANSWER_KEY, userAnswer)
                    putInt(POSITION_KEY, position)
                }
            }
    }
}