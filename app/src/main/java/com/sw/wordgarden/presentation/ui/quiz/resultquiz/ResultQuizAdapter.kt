package com.sw.wordgarden.presentation.ui.quiz.resultquiz

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.sw.wordgarden.presentation.model.QAModel
import com.sw.wordgarden.presentation.util.Constants.QUESTION_TYPE_FOUR
import com.sw.wordgarden.presentation.util.Constants.QUESTION_TYPE_OX

class ResultQuizAdapter(
    fragment: Fragment,
    private val quizList: List<QAModel>,
    private val onNextClicked: (
        position: Int,
        question: String,
        word: String,
        answer: String,
        options: List<String>,
        userAnswer: String,
        isCorrect: Boolean
    ) -> Unit
) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = quizList.size

    override fun createFragment(position: Int): Fragment {
        val quizItem = quizList[position]

        return when (quizItem.questionType) {
            QUESTION_TYPE_OX -> {
                ResultQuestionOXFragment.newInstance(
                    question = quizItem.question ?: "",
                    answer = quizItem.correctAnswer ?: "",
                    userAnswer = quizItem.userAnswer ?: "",
                    position = position
                ).apply {
                    setOnNextClickedListener { position, question, answer, userAnswer, isCorrect ->
                        onNextClicked(position, question, "", answer, emptyList(), userAnswer, isCorrect)
                    }
                }
            }

            QUESTION_TYPE_FOUR -> {
                ResultQuestionChoiceFragment.newInstance(
                    question = quizItem.question ?: "",
                    word = quizItem.word ?: "",
                    answer = quizItem.correctAnswer ?: "",
                    options = quizItem.options ?: emptyList(),
                    userAnswer = quizItem.userAnswer ?: "",
                    position = position
                ).apply {
                    setOnNextClickedListener { position, question, word, answer, options, userAnswer, isCorrect ->
                        onNextClicked(position, question, word, answer, options, userAnswer, isCorrect)
                    }
                }
            }

            else -> {
                ResultQuestionWritingFragment.newInstance(
                    question = quizItem.question ?: "",
                    answer = quizItem.correctAnswer ?: "",
                    userAnswer = quizItem.userAnswer ?: "",
                    position = position
                ).apply {
                    setOnNextClickedListener { position, question, answer, userAnswer, isCorrect ->
                        onNextClicked(position, question, "", answer, emptyList(), userAnswer, isCorrect)
                    }
                }
            }
        }
    }
}