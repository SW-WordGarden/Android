package com.sw.wordgarden.presentation.ui.quiz.solvequiz

import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import com.sw.wordgarden.R
import com.sw.wordgarden.databinding.FragmentSolveQuizQuestionBinding
import com.sw.wordgarden.presentation.util.ToastMaker

class SolveQuizQuestionFragment : Fragment() {

    private var _binding: FragmentSolveQuizQuestionBinding? = null
    private val binding get() = _binding!!

    private lateinit var question: String
    private lateinit var answer: String
    private var position: Int = 0

    private var onNextClicked: ((position: Int, question: String, answer: String, isFull: Boolean) -> Unit)? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            question = it.getString(QUESTION_KEY).toString()
            answer = it.getString(ANSWER_KEY).toString()
            position = it.getInt(POSITION_KEY)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSolveQuizQuestionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUi()
        setupListener()
    }

    private fun setupUi() = with(binding) {
        tvSolveQuizItemQuestion.text = question

        if (position < QUIZ_SIZE - 1) {
            btnSolveQuizSubmit.text = getString(R.string.solve_quiz_next)
        } else {
            btnSolveQuizSubmit.text = getString(R.string.solve_quiz_submit)
        }
    }

    private fun setupListener() = with(binding) {
        etSolveQuizItemFillAnswer.setOnEditorActionListener { _, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE || event.keyCode == KeyEvent.KEYCODE_ENTER) {
                val userAnswer = etSolveQuizItemFillAnswer.text.toString()
                onNextClicked?.invoke(position, question, userAnswer, true)
                true
            } else {
                false
            }
        }

        btnSolveQuizSubmit.setOnClickListener {
            val answer = etSolveQuizItemFillAnswer.text.toString()

            if (answer.isEmpty()) {
                onNextClicked?.invoke(position, "", answer, false)

                ToastMaker.make(requireContext(), R.string.solve_quiz_msg_need_answer)
            } else {
                onNextClicked?.invoke(position, "", answer, true)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun setOnNextClickedListener(listener: (Int, String, String, Boolean) -> Unit) {
        onNextClicked = listener
    }

    companion object {
        const val QUESTION_KEY = "QUESTION_KEY"
        const val ANSWER_KEY = "ANSWER_KEY"
        const val POSITION_KEY = "POSITION_KEY"
        const val QUIZ_SIZE = 10

        fun newInstance(question: String, answer: String, position: Int) =
            SolveQuizQuestionFragment().apply {
                arguments = Bundle().apply {
                    putString(QUESTION_KEY, question)
                    putString(ANSWER_KEY, answer)
                    putInt(POSITION_KEY, position)
                }
            }
    }
}