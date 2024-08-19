package com.sw.wordgarden.presentation.ui.quiz.solvequiz

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.sw.wordgarden.presentation.model.QAModel

class SolveQuizAdapter(
    fragment: Fragment,
    private val quizList: List<QAModel>,
    private val onNextClicked: (position: Int, question: String, answer: String, isFull: Boolean) -> Unit
) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = quizList.size

    override fun createFragment(position: Int): Fragment {
        val quizItem = quizList[position]

        return SolveQuizQuestionFragment.newInstance(quizItem.question ?: "", quizItem.userAnswer ?: "", position).apply {
            setOnNextClickedListener { position, question, answer, isFull ->
                onNextClicked(position, question, answer, isFull)
            }
        }
    }
}