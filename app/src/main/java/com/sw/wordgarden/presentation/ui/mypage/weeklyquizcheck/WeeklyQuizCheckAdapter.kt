package com.sw.wordgarden.presentation.ui.mypage.weeklyquizcheck

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sw.wordgarden.databinding.ItemWeeklyQuizCheckBinding
import com.sw.wordgarden.domain.entity.quiz.WqWrongAnswerEntity

class WeeklyQuizCheckAdapter() :
    ListAdapter<WqWrongAnswerEntity, WeeklyQuizCheckAdapter.WeeklyQuizCheckViewHolder>(DIFF_UTIL) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeeklyQuizCheckViewHolder {
        return WeeklyQuizCheckViewHolder(
            ItemWeeklyQuizCheckBinding.inflate(LayoutInflater.from(parent.context), parent, false),
        )
    }

    override fun onBindViewHolder(holder: WeeklyQuizCheckViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class WeeklyQuizCheckViewHolder(
        private val binding: ItemWeeklyQuizCheckBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(wrongEntity: WqWrongAnswerEntity) = with(binding) {
            tvWeeklyQuizCheckItemWord.text = wrongEntity.word
            tvWeeklyQuizCheckItemMeaning.text = wrongEntity.wordInfo
        }
    }

    companion object {
        private val DIFF_UTIL = object : DiffUtil.ItemCallback<WqWrongAnswerEntity>() {
            override fun areItemsTheSame(oldItem: WqWrongAnswerEntity, newItem: WqWrongAnswerEntity): Boolean {
                return oldItem.wqId == newItem.wqId
            }

            override fun areContentsTheSame(oldItem: WqWrongAnswerEntity, newItem: WqWrongAnswerEntity): Boolean {
                return oldItem == newItem
            }
        }
    }
}


