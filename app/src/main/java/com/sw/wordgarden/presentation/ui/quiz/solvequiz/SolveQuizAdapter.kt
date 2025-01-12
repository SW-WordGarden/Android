package com.sw.wordgarden.presentation.ui.quiz.solvequiz

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.sw.wordgarden.presentation.model.QAModel
import com.sw.wordgarden.presentation.util.Constants.QUESTION_TYPE_CHOICE
import com.sw.wordgarden.presentation.util.Constants.QUESTION_TYPE_OX

class SolveQuizAdapter(
    fragment: Fragment,
    private val quizList: List<QAModel>,
    private val enteredList: List<QAModel>,
    private val onNextClicked: (
        position: Int,
        qid: String,
        question: String,
        word: String,
        answer: String,
        options: List<String>,
        isFull: Boolean,
        isNext: Boolean
    ) -> Unit
) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = quizList.size

    override fun createFragment(position: Int): Fragment {
        val quizItem = quizList[position]
        val enteredItem = enteredList[position]

        return when (quizItem.questionType) {
            QUESTION_TYPE_OX -> {
                SolveQuestionOXFragment.newInstance(
                    qid = quizItem.questionId ?: "",
                    question = quizItem.question ?: "",
                    answer = quizItem.userAnswer ?: "",
                    position = position
                ).apply {
                    setOnNextClickedListener { position, qid, question, answer,  isFull, isNext ->
                        onNextClicked(position, qid, question, "", answer, emptyList(), isFull, isNext)
                    }
                }
            }

            QUESTION_TYPE_CHOICE -> {
                SolveQuestionChoiceFragment.newInstance(
                    qid = quizItem.questionId ?: "",
                    question = quizItem.question ?: "",
                    word = quizItem.word ?: "",
                    answer = quizItem.userAnswer ?: "",
                    enteredAnswer = enteredItem.userAnswer ?: "",
                    options = quizItem.options ?: emptyList(),
                    position = position
                ).apply {
                    setOnNextClickedListener { position, qid, question, word, answer, options, isFull, isNext ->
                        onNextClicked(position, qid, question, word, answer, options, isFull, isNext)
                    }
                }
            }

            else -> {
                SolveQuestionWritingFragment.newInstance(
                    qid = quizItem.questionId ?: "",
                    question = quizItem.question ?: "",
                    answer = quizItem.userAnswer ?: "",
                    position = position
                ).apply {
                    setOnNextClickedListener { position, qid, question, answer, isFull, isNext ->
                        onNextClicked(position, qid, question, "", answer, emptyList(), isFull, isNext)
                    }
                }
            }
        }
    }
}