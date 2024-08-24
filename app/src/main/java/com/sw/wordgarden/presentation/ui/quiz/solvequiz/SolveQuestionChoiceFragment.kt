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
    private lateinit var options: List<String>
    private var position: Int = 0

    private var onNextClicked: ((position: Int, qid: String, question: String, word: String, answer: String, options: List<String>, isFull: Boolean) -> Unit)? =
        null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            qid = it.getString(QUESTION_ID_KEY).toString()
            question = it.getString(QUESTION_KEY).toString()
            word = it.getString(WORD_KEY).toString()
            answer = it.getString(ANSWER_KEY).toString()
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
        }
        llSolveQuizItemChoice2.setOnClickListener {
            checkedItem = options[1]
            ivSolveQuizItemChoice1.setImageResource(R.drawable.ic_select_1)
            ivSolveQuizItemChoice2.setImageResource(R.drawable.ic_select_checked)
            ivSolveQuizItemChoice3.setImageResource(R.drawable.ic_select_3)
            ivSolveQuizItemChoice4.setImageResource(R.drawable.ic_select_4)
        }
        llSolveQuizItemChoice3.setOnClickListener {
            checkedItem = options[2]
            ivSolveQuizItemChoice1.setImageResource(R.drawable.ic_select_1)
            ivSolveQuizItemChoice2.setImageResource(R.drawable.ic_select_2)
            ivSolveQuizItemChoice3.setImageResource(R.drawable.ic_select_checked)
            ivSolveQuizItemChoice4.setImageResource(R.drawable.ic_select_4)
        }
        llSolveQuizItemChoice4.setOnClickListener {
            checkedItem = options[3]
            ivSolveQuizItemChoice1.setImageResource(R.drawable.ic_select_1)
            ivSolveQuizItemChoice2.setImageResource(R.drawable.ic_select_2)
            ivSolveQuizItemChoice3.setImageResource(R.drawable.ic_select_3)
            ivSolveQuizItemChoice4.setImageResource(R.drawable.ic_select_checked)
        }

        btnSolveQuizQuestionSubmitChoice.setOnClickListener {
            if (checkedItem == "") {
                onNextClicked?.invoke(position, qid, "", "", checkedItem, emptyList(), false)
                ToastMaker.make(requireContext(), R.string.solve_quiz_msg_need_select_answer)
            } else {
                onNextClicked?.invoke(position, qid, "", "", checkedItem, emptyList(), true)
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

    fun setOnNextClickedListener(listener: (Int, String, String, String, String, List<String>, Boolean) -> Unit) {
        onNextClicked = listener
    }

    companion object {
        const val QUESTION_ID_KEY = "QUESTION_ID_KEY"
        const val QUESTION_KEY = "QUESTION_KEY"
        const val WORD_KEY = "WORD_KEY"
        const val ANSWER_KEY = "ANSWER_KEY"
        const val OPTIONS_KEY = "OPTIONS_KEY"
        const val POSITION_KEY = "POSITION_KEY"

        fun newInstance(
            qid: String,
            question: String,
            word: String,
            answer: String,
            options: List<String>,
            position: Int
        ) =
            SolveQuestionChoiceFragment().apply {
                arguments = Bundle().apply {
                    putString(QUESTION_ID_KEY, qid)
                    putString(QUESTION_KEY, question)
                    putString(WORD_KEY, word)
                    putString(ANSWER_KEY, answer)
                    putStringArrayList(OPTIONS_KEY, ArrayList(options))
                    putInt(POSITION_KEY, position)
                }
            }
    }
}