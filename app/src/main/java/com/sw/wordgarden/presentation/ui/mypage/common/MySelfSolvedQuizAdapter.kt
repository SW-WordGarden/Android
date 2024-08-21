package com.sw.wordgarden.presentation.ui.mypage.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sw.wordgarden.databinding.ItemSelfSolvedQuizBinding
import com.sw.wordgarden.domain.entity.quiz.QuizSummaryEntity

class MySelfSolvedQuizAdapter(
    private val quizListItemListener: QuizItemListener
) : ListAdapter<QuizSummaryEntity, MySelfSolvedQuizAdapter.MySelfQuizViewHolder>(DIFF_UTIL) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MySelfQuizViewHolder {
        return MySelfQuizViewHolder(
            ItemSelfSolvedQuizBinding.inflate(LayoutInflater.from(parent.context), parent, false),
        )
    }

    override fun onBindViewHolder(holder: MySelfQuizViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class MySelfQuizViewHolder(
        private val binding: ItemSelfSolvedQuizBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(quizSummaryModel: QuizSummaryEntity) = with(binding) {
            tvSelfSolvedItem.text = quizSummaryModel.title

            ivSelfSolvedDetailItem.setOnClickListener {
                quizListItemListener.onItemClicked(quizSummaryModel.quizId ?: "", quizSummaryModel.title ?: "")
            }
        }
    }

    companion object {
        private val DIFF_UTIL = object : DiffUtil.ItemCallback<QuizSummaryEntity>() {
            override fun areItemsTheSame(oldItem: QuizSummaryEntity, newItem: QuizSummaryEntity): Boolean {
                return oldItem.quizId == newItem.quizId
            }

            override fun areContentsTheSame(oldItem: QuizSummaryEntity, newItem: QuizSummaryEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    interface QuizItemListener {
        fun onItemClicked(sqId: String, qTitle: String)
    }
}


