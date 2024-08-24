package com.sw.wordgarden.presentation.ui.quiz.makequiz

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.sw.wordgarden.presentation.model.QAModel

class MakeQuizAdapter(
    fragment: Fragment,
    private val isEnableMode: Boolean,
    private val quizList: List<QAModel>,
    private val onNextClicked: (position: Int, question: String, answer: String, isFull: Boolean) -> Unit
) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = quizList.size

    override fun createFragment(position: Int): Fragment {
        val quizItem = quizList[position]

        return MakeQuizQuestionFragment.newInstance(isEnableMode, quizItem.question, quizItem.correctAnswer, position).apply {
            setOnNextClickedListener { position, question, answer, isFull ->
                onNextClicked(position, question, answer, isFull)
            }
        }
    }
}