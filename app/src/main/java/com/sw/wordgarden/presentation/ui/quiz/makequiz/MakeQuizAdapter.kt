package com.sw.wordgarden.presentation.ui.quiz.makequiz

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.sw.wordgarden.presentation.model.QuizModel

class MakeQuizAdapter(
    fragment: Fragment,
    private val isEnableMode: Boolean,
    private val quizList: List<QuizModel>,
    private val onNextClicked: (position: Int, question: String, answer: String, isFull: Boolean) -> Unit
) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = quizList.size

    override fun createFragment(position: Int): Fragment {
        val quizItem = quizList[position]

        return MakeQuizQuestionFragment.newInstance(isEnableMode, quizItem.question, quizItem.answer, position).apply {
            setOnNextClickedListener { position, question, answer, isFull ->
                onNextClicked(position, question, answer, isFull)
            }
        }
    }
}