package com.sw.wordgarden.presentation.ui.mypage.mymadequiz

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sw.wordgarden.databinding.ItemMadeSolvedQuizBinding

class MyMadeQuizAdapter(
    private val quizListItemListener: QuizItemListener
) : ListAdapter<String, MyMadeQuizAdapter.MyMadeQuizViewHolder>(DIFF_UTIL) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyMadeQuizViewHolder {
        return MyMadeQuizViewHolder(
            ItemMadeSolvedQuizBinding.inflate(LayoutInflater.from(parent.context), parent, false),
        )
    }

    override fun onBindViewHolder(holder: MyMadeQuizViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class MyMadeQuizViewHolder(
        private val binding: ItemMadeSolvedQuizBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(title: String) = with(binding) {
            tvMadeSolvedItem.text = title

            ivMadeSolvedDetailItem.setOnClickListener {
                quizListItemListener.onItemClicked(title)
            }
        }
    }

    companion object {
        private val DIFF_UTIL = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }
        }
    }

    interface QuizItemListener {
        fun onItemClicked(title: String)
    }
}


