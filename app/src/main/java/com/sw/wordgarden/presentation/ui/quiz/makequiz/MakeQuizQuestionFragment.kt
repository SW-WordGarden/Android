package com.sw.wordgarden.presentation.ui.quiz.makequiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sw.wordgarden.R
import com.sw.wordgarden.databinding.FragmentMakeQuizQuestionBinding
import com.sw.wordgarden.presentation.util.ToastMaker

class MakeQuizQuestionFragment : Fragment() {

    private var _binding: FragmentMakeQuizQuestionBinding? = null
    private val binding get() = _binding!!

    private lateinit var question: String
    private lateinit var answer: String
    private var position: Int = 0
    private var enableMode = true

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
        _binding = FragmentMakeQuizQuestionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUi()
        setupListener()
    }

    private fun setupUi() = with(binding) {
        if (position < QUIZ_SIZE - 1) {
            btnMakeQuizNext.text = getString(R.string.make_quiz_next)
        } else {
            btnMakeQuizNext.text = getString(R.string.make_quiz_share)
        }
    }

    private fun setupListener() = with(binding) {
        if (!enableMode) {
            etMakeQuizItemQuestion.isEnabled = false
            etMakeQuizItemAnswer.isEnabled = false
        }
        etMakeQuizItemQuestion.setText(question)
        etMakeQuizItemAnswer.setText(answer)

        btnMakeQuizNext.setOnClickListener {
            val newQuestion = etMakeQuizItemQuestion.text.toString()
            val newAnswer = etMakeQuizItemAnswer.text.toString()

            if (newQuestion.isEmpty() || newAnswer.isEmpty()) {
                onNextClicked?.invoke(position, newQuestion, newAnswer, false)

                ToastMaker.make(requireContext(), R.string.make_quiz_msg_need_question_answer)
            } else {
                onNextClicked?.invoke(position, newQuestion, newAnswer, true)
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

        fun newInstance(isEnableMode: Boolean, question: String?, answer: String?, position: Int) =
            MakeQuizQuestionFragment().apply {
                enableMode = isEnableMode
                arguments = Bundle().apply {
                    putString(QUESTION_KEY, question)
                    putString(ANSWER_KEY, answer)
                    putInt(POSITION_KEY, position)
                }
            }
    }
}