package com.sw.wordgarden.presentation.ui.quiz.makequiz

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.sw.wordgarden.presentation.model.SelfQuizModel

class MakeQuizAdapter(
    fragment: Fragment,
    private val quizList: List<SelfQuizModel>,
    private val onNextClicked: (position: Int, question: String, answer: String, isFull: Boolean) -> Unit
) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = quizList.size

    override fun createFragment(position: Int): Fragment {
        val quizItem = quizList[position]

        return QuizQuestionFragment.newInstance(quizItem.question, quizItem.answer, position).apply {
            setOnNextClickedListener { position, question, answer, isFull ->
                onNextClicked(position, question, answer, isFull)
            }
        }
    }
}