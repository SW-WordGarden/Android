package com.sw.wordgarden.presentation.ui.quiz.resultquiz

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sw.wordgarden.R
import com.sw.wordgarden.databinding.FragmentResultQuestionChoiceBinding

class ResultQuestionChoiceFragment : Fragment() {

    private var _binding: FragmentResultQuestionChoiceBinding? = null
    private val binding get() = _binding!!

    private lateinit var question: String
    private lateinit var word: String
    private lateinit var answer: String
    private lateinit var options: List<String>
    private lateinit var userAnswer: String
    private var position: Int = 0

    private var onNextClicked: ((position: Int, question: String, word: String, answer: String, options: List<String>, userAnswer: String, isCorrect: Boolean) -> Unit)? =
        null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            question = it.getString(QUESTION_KEY).toString()
            word = it.getString(WORD_KEY).toString()
            answer = it.getString(ANSWER_KEY).toString()
            options = it.getStringArrayList(OPTIONS_KEY) ?: emptyList()
            userAnswer = it.getString(USER_ANSWER_KEY).toString()
            position = it.getInt(POSITION_KEY)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResultQuestionChoiceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUi()
    }

    @SuppressLint("SetTextI18n")
    private fun setupUi() = with(binding) {
        tvResultQuizItemQuestionChoiceQuestion.text = word
        tvResultQuizItemChoice1.text = options[0]
        tvResultQuizItemChoice2.text = options[1]
        tvResultQuizItemChoice3.text = options[2]
        tvResultQuizItemChoice4.text = options[3]

        val optionsMap = options.withIndex().associate { (it.index + 1) to it.value}
        val correctAnswerNumber = optionsMap.filterValues { it == answer }.keys.firstOrNull()
        val userAnswerNumber = optionsMap.filterValues { it == userAnswer }.keys.firstOrNull()

        if (correctAnswerNumber == userAnswerNumber) {
            when (correctAnswerNumber) {
                1 -> { ivResultQuizItemChoice1.setImageResource(R.drawable.ic_select_checked) }
                2 -> { ivResultQuizItemChoice2.setImageResource(R.drawable.ic_select_checked) }
                3 -> { ivResultQuizItemChoice3.setImageResource(R.drawable.ic_select_checked) }
                4 -> { ivResultQuizItemChoice4.setImageResource(R.drawable.ic_select_checked) }
            }
        } else {
            when (correctAnswerNumber) {
                1 -> { ivResultQuizItemChoice1.setImageResource(R.drawable.ic_select_checked) }
                2 -> { ivResultQuizItemChoice2.setImageResource(R.drawable.ic_select_checked) }
                3 -> { ivResultQuizItemChoice3.setImageResource(R.drawable.ic_select_checked) }
                4 -> { ivResultQuizItemChoice4.setImageResource(R.drawable.ic_select_checked) }
            }
            when (userAnswerNumber) {
                1 -> { ivResultQuizItemChoice1.setImageResource(R.drawable.ic_select_wrong) }
                2 -> { ivResultQuizItemChoice2.setImageResource(R.drawable.ic_select_wrong) }
                3 -> { ivResultQuizItemChoice3.setImageResource(R.drawable.ic_select_wrong) }
                4 -> { ivResultQuizItemChoice4.setImageResource(R.drawable.ic_select_wrong) }
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

    fun setOnNextClickedListener(listener: (Int, String, String, String, List<String>, String, Boolean) -> Unit) {
        onNextClicked = listener
    }

    companion object {
        const val QUESTION_KEY = "QUESTION_KEY"
        const val WORD_KEY = "WORD_KEY"
        const val ANSWER_KEY = "ANSWER_KEY"
        const val OPTIONS_KEY = "OPTIONS_KEY"
        const val USER_ANSWER_KEY = "USER_ANSWER_KEY"
        const val POSITION_KEY = "POSITION_KEY"

        fun newInstance(
            question: String,
            word: String,
            answer: String,
            options: List<String>,
            userAnswer: String,
            position: Int
        ) =
            ResultQuestionChoiceFragment().apply {
                arguments = Bundle().apply {
                    putString(QUESTION_KEY, question)
                    putString(WORD_KEY, word)
                    putString(ANSWER_KEY, answer)
                    putStringArrayList(OPTIONS_KEY, ArrayList(options))
                    putString(USER_ANSWER_KEY, userAnswer)
                    putInt(POSITION_KEY, position)
                }
            }
    }
}