package com.sw.wordgarden.presentation.ui.quiz.solvequiz

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sw.wordgarden.R
import com.sw.wordgarden.databinding.FragmentSolveQuestionChoiceBinding
import com.sw.wordgarden.presentation.util.Constants.QUIZ_AMOUNT
import com.sw.wordgarden.presentation.util.ToastMaker

class SolveQuestionChoiceFragment : Fragment() {

    private var _binding: FragmentSolveQuestionChoiceBinding? = null
    private val binding get() = _binding!!

    private lateinit var qid: String
    private lateinit var question: String
    private lateinit var word: String
    private lateinit var answer: String
    private lateinit var enteredAnswer: String
    private lateinit var options: List<String>
    private var position: Int = 0

    private var onNextClicked: ((position: Int, qid: String, question: String, word: String, answer: String, options: List<String>, isFull: Boolean, isNext: Boolean) -> Unit)? =
        null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            qid = it.getString(QUESTION_ID_KEY).toString()
            question = it.getString(QUESTION_KEY).toString()
            word = it.getString(WORD_KEY).toString()
            answer = it.getString(ANSWER_KEY).toString()
            enteredAnswer = it.getString(ENTERED_ANSWER_KEY).toString()
            options = it.getStringArrayList(OPTIONS_KEY) ?: emptyList()
            position = it.getInt(POSITION_KEY)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSolveQuestionChoiceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUi()
        setupListener()
    }

    private fun setupUi() = with(binding) {
        tvSolveQuizItemQuestionChoiceTitle.text = question
        tvSolveQuizItemQuestionChoiceQuestion.text = word

        for (i in 0..3) {
            if (enteredAnswer == options[i]) {
                when (i) {
                    0 -> {
                        ivSolveQuizItemChoice1.setImageResource(R.drawable.ic_select_checked)
                    }

                    1 -> {
                        ivSolveQuizItemChoice2.setImageResource(R.drawable.ic_select_checked)
                    }

                    2 -> {
                        ivSolveQuizItemChoice3.setImageResource(R.drawable.ic_select_checked)
                    }

                    3 -> {
                        ivSolveQuizItemChoice4.setImageResource(R.drawable.ic_select_checked)
                    }
                }
            }
        }

        if (position < QUIZ_AMOUNT - 1) {
            btnSolveQuizQuestionSubmitChoice.text = getString(R.string.solve_quiz_next)
        } else {
            btnSolveQuizQuestionSubmitChoice.text = getString(R.string.solve_quiz_submit)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setupListener() = with(binding) {
        tvSolveQuizItemChoice1.text = options[0]
        tvSolveQuizItemChoice2.text = options[1]
        tvSolveQuizItemChoice3.text = options[2]
        tvSolveQuizItemChoice4.text = options[3]

        var checkedItem = ""
        llSolveQuizItemChoice1.setOnClickListener {
            checkedItem = options[0]
            ivSolveQuizItemChoice1.setImageResource(R.drawable.ic_select_checked)
            ivSolveQuizItemChoice2.setImageResource(R.drawable.ic_select_2)
            ivSolveQuizItemChoice3.setImageResource(R.drawable.ic_select_3)
            ivSolveQuizItemChoice4.setImageResource(R.drawable.ic_select_4)

            checkAnswer(checkedItem)
        }
        llSolveQuizItemChoice2.setOnClickListener {
            checkedItem = options[1]
            ivSolveQuizItemChoice1.setImageResource(R.drawable.ic_select_1)
            ivSolveQuizItemChoice2.setImageResource(R.drawable.ic_select_checked)
            ivSolveQuizItemChoice3.setImageResource(R.drawable.ic_select_3)
            ivSolveQuizItemChoice4.setImageResource(R.drawable.ic_select_4)

            checkAnswer(checkedItem)
        }
        llSolveQuizItemChoice3.setOnClickListener {
            checkedItem = options[2]
            ivSolveQuizItemChoice1.setImageResource(R.drawable.ic_select_1)
            ivSolveQuizItemChoice2.setImageResource(R.drawable.ic_select_2)
            ivSolveQuizItemChoice3.setImageResource(R.drawable.ic_select_checked)
            ivSolveQuizItemChoice4.setImageResource(R.drawable.ic_select_4)

            checkAnswer(checkedItem)
        }
        llSolveQuizItemChoice4.setOnClickListener {
            checkedItem = options[3]
            ivSolveQuizItemChoice1.setImageResource(R.drawable.ic_select_1)
            ivSolveQuizItemChoice2.setImageResource(R.drawable.ic_select_2)
            ivSolveQuizItemChoice3.setImageResource(R.drawable.ic_select_3)
            ivSolveQuizItemChoice4.setImageResource(R.drawable.ic_select_checked)

            checkAnswer(checkedItem)
        }

        btnSolveQuizQuestionSubmitChoice.setOnClickListener {
            if (checkedItem == "") {
                onNextClicked?.invoke(position, qid, "", "", checkedItem, emptyList(), false, true)
                ToastMaker.make(requireContext(), R.string.solve_quiz_msg_need_select_answer)
            } else {
                onNextClicked?.invoke(position, qid, "", "", checkedItem, emptyList(), true, true)
            }
        }
    }

    private fun checkAnswer(userAnswer: String) {
        if (userAnswer != "") {
            onNextClicked?.invoke(position, qid, "", "", userAnswer, emptyList(), true, false)
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

    fun setOnNextClickedListener(listener: (Int, String, String, String, String, List<String>, Boolean, Boolean) -> Unit) {
        onNextClicked = listener
    }

    companion object {
        const val QUESTION_ID_KEY = "QUESTION_ID_KEY"
        const val QUESTION_KEY = "QUESTION_KEY"
        const val WORD_KEY = "WORD_KEY"
        const val ANSWER_KEY = "ANSWER_KEY"
        const val ENTERED_ANSWER_KEY = "ENTERED_ANSWER_KEY"
        const val OPTIONS_KEY = "OPTIONS_KEY"
        const val POSITION_KEY = "POSITION_KEY"

        fun newInstance(
            qid: String,
            question: String,
            word: String,
            answer: String,
            enteredAnswer: String,
            options: List<String>,
            position: Int
        ) =
            SolveQuestionChoiceFragment().apply {
                arguments = Bundle().apply {
                    putString(QUESTION_ID_KEY, qid)
                    putString(QUESTION_KEY, question)
                    putString(WORD_KEY, word)
                    putString(ANSWER_KEY, answer)
                    putString(ENTERED_ANSWER_KEY, enteredAnswer)
                    putStringArrayList(OPTIONS_KEY, ArrayList(options))
                    putInt(POSITION_KEY, position)
                }
            }
    }
}