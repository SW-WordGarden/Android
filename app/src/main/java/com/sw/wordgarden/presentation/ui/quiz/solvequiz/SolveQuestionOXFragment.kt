package com.sw.wordgarden.presentation.ui.quiz.solvequiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sw.wordgarden.R
import com.sw.wordgarden.databinding.FragmentSolveQuestionOXBinding
import com.sw.wordgarden.presentation.util.Constants.QUESTION_O
import com.sw.wordgarden.presentation.util.Constants.QUESTION_OX_TITLE
import com.sw.wordgarden.presentation.util.Constants.QUESTION_X
import com.sw.wordgarden.presentation.util.Constants.QUIZ_AMOUNT
import com.sw.wordgarden.presentation.util.ToastMaker

class SolveQuestionOXFragment : Fragment() {

    private var _binding: FragmentSolveQuestionOXBinding? = null
    private val binding get() = _binding!!

    private lateinit var qid: String
    private lateinit var question: String
    private lateinit var answer: String
    private var position: Int = 0

    private var onNextClicked: ((position: Int, qid: String, question: String, answer: String, isFull: Boolean, isNext: Boolean) -> Unit)? =
        null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            qid = it.getString(QUESTION_ID_KEY).toString()
            question = it.getString(QUESTION_KEY).toString()
            answer = it.getString(ANSWER_KEY).toString()
            position = it.getInt(POSITION_KEY)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSolveQuestionOXBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUi()
        setupListener()
    }

    private fun setupUi() = with(binding) {
        tvSolveQuizItemQuestionOxTitle.text = QUESTION_OX_TITLE

        val question = question.substringAfter(QUESTION_OX_TITLE)
        tvSolveQuizItemQuestionOxQuestion.text = question

        if (position < QUIZ_AMOUNT - 1) {
            btnSolveQuizQuestionSubmitOx.text = getString(R.string.solve_quiz_next)
        } else {
            btnSolveQuizQuestionSubmitOx.text = getString(R.string.solve_quiz_submit)
        }
    }

    private fun setupListener() = with(binding) {
        var checkedItem = ""

        rgSolveQuizItemQuestionOx.setOnCheckedChangeListener { _, _ ->
            checkedItem =
                when (rgSolveQuizItemQuestionOx.checkedRadioButtonId) {
                    R.id.rb_solve_quiz_item_question_o -> { QUESTION_O }
                    R.id.rb_solve_quiz_item_question_x -> { QUESTION_X }
                    else -> { "" }
                }

            onNextClicked?.invoke(position, qid, "", checkedItem, true, false)
        }

        btnSolveQuizQuestionSubmitOx.setOnClickListener {
            if (checkedItem == "") {
                onNextClicked?.invoke(position, qid, "", checkedItem, false, true)
                ToastMaker.make(requireContext(), R.string.solve_quiz_msg_need_select_answer)
            } else {
                onNextClicked?.invoke(position, qid, "", checkedItem, true, true)
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

    fun setOnNextClickedListener(listener: (Int, String, String, String, Boolean, Boolean) -> Unit) {
        onNextClicked = listener
    }

    companion object {
        const val QUESTION_ID_KEY = "QUESTION_ID_KEY"
        const val QUESTION_KEY = "QUESTION_KEY"
        const val ANSWER_KEY = "ANSWER_KEY"
        const val POSITION_KEY = "POSITION_KEY"

        fun newInstance(qid: String, question: String, answer: String, position: Int) =
            SolveQuestionOXFragment().apply {
                arguments = Bundle().apply {
                    putString(QUESTION_ID_KEY, qid)
                    putString(QUESTION_KEY, question)
                    putString(ANSWER_KEY, answer)
                    putInt(POSITION_KEY, position)
                }
            }
    }
}