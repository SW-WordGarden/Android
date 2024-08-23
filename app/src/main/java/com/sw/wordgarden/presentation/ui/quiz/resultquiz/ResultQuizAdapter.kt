package com.sw.wordgarden.presentation.ui.quiz.resultquiz

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.sw.wordgarden.presentation.model.QAModel

class ResultQuizAdapter(
    fragment: Fragment,
    private val quizList: List<QAModel>,
    private val onNextClicked: (position: Int, question: String, answer: String, userAnswer: String, isCorrect: Boolean) -> Unit
) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = quizList.size

    override fun createFragment(position: Int): Fragment {
        val quizItem = quizList[position]

        return ResultQuestionWritingFragment.newInstance(
            quizItem.question ?: "",
            quizItem.correctAnswer ?: "",
            quizItem.userAnswer ?: "",
            position
        ).apply {
            setOnNextClickedListener { position, question, answer, userAnswer, isCorrect ->
                onNextClicked(position, question, answer, userAnswer, isCorrect)
            }
        }
    }
}