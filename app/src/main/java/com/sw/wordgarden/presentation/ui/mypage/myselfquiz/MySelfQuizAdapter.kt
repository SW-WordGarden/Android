package com.sw.wordgarden.presentation.ui.mypage.myselfquiz

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sw.wordgarden.databinding.ItemSelfSolvedQuizBinding
import com.sw.wordgarden.presentation.model.QuizSummaryModel

class MySelfQuizAdapter(
    private val quizListItemListener: QuizItemListener
) : ListAdapter<QuizSummaryModel, MySelfQuizAdapter.MySelfQuizViewHolder>(DIFF_UTIL) {

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
        fun bind(quizSummaryModel: QuizSummaryModel) = with(binding) {
            tvSelfSolvedItem.text = quizSummaryModel.title

            ivSelfSolvedDetailItem.setOnClickListener {
                quizListItemListener.onItemClicked(quizSummaryModel.quizId ?: "")
            }
        }
    }

    companion object {
        private val DIFF_UTIL = object : DiffUtil.ItemCallback<QuizSummaryModel>() {
            override fun areItemsTheSame(oldItem: QuizSummaryModel, newItem: QuizSummaryModel): Boolean {
                return oldItem.quizId == newItem.quizId
            }

            override fun areContentsTheSame(oldItem: QuizSummaryModel, newItem: QuizSummaryModel): Boolean {
                return oldItem == newItem
            }
        }
    }

    interface QuizItemListener {
        fun onItemClicked(quizId: String)
    }
}


