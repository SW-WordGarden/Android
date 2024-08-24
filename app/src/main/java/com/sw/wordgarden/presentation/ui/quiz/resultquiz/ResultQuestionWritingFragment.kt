package com.sw.wordgarden.presentation.ui.quiz.resultquiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sw.wordgarden.R
import com.sw.wordgarden.databinding.FragmentResultQuestionWritingBinding
import com.sw.wordgarden.presentation.util.Constants.QUESTION_WRITE_TITLE

class ResultQuestionWritingFragment : Fragment() {

    private var _binding: FragmentResultQuestionWritingBinding? = null
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
        _binding = FragmentResultQuestionWritingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUi()
    }

    private fun setupUi() = with(binding) {
        val question = question.substringAfter(QUESTION_WRITE_TITLE)
        tvResultQuizItemQuestionQuestion.text = question

        val answer =
            getString(R.string.result_quiz_correct) + " " + this@ResultQuestionWritingFragment.answer
        val userAnswer =
            getString(R.string.result_quiz_answer) + " " + this@ResultQuestionWritingFragment.userAnswer

        if (this@ResultQuestionWritingFragment.answer == this@ResultQuestionWritingFragment.userAnswer) {
            tvResultQuizItemCorrect.text = answer
            tvResultQuizItemAnswer.visibility = View.GONE
        } else {
            tvResultQuizItemCorrect.text = answer
            tvResultQuizItemAnswer.text = userAnswer

            val params = tvResultQuizItemCorrect.layoutParams as ViewGroup.MarginLayoutParams
            params.bottomMargin = resources.getDimensionPixelSize(R.dimen.margin_extra_small)
            tvResultQuizItemCorrect.layoutParams = params
            tvResultQuizItemAnswer.visibility = View.VISIBLE
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

        fun newInstance(question: String, answer: String, userAnswer: String, position: Int) =
            ResultQuestionWritingFragment().apply {
                arguments = Bundle().apply {
                    putString(QUESTION_KEY, question)
                    putString(ANSWER_KEY, answer)
                    putString(USER_ANSWER_KEY, userAnswer)
                    putInt(POSITION_KEY, position)
                }
            }
    }
}