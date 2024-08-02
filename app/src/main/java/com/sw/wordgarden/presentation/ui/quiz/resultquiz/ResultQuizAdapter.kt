package com.sw.wordgarden.presentation.ui.quiz.resultquiz

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.sw.wordgarden.domain.entity.QuizEntity
import com.sw.wordgarden.domain.entity.QuizResultEntity

class ResultQuizAdapter(
    fragment: Fragment,
    private val quizList: List<QuizEntity>,
    private val resultList: List<QuizResultEntity>,
    private val onNextClicked: (position: Int, question: String, answer: String, userAnswer: String, isCorrect: Boolean) -> Unit
) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = quizList.size

    override fun createFragment(position: Int): Fragment {
        val quizItem = quizList[position]
        val resultItem = resultList[position]

        return ResultQuizQuestionFragment.newInstance(
            quizItem.question ?: "",
            quizItem.answer ?: "",
            resultItem.userAnswer ?: "",
            position
        ).apply {
            setOnNextClickedListener { position, question, answer, userAnswer, isCorrect ->
                onNextClicked(position, question, answer, userAnswer, isCorrect)
            }
        }
    }
}