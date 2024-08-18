package com.sw.wordgarden.presentation.ui.alarm

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sw.wordgarden.R
import com.sw.wordgarden.databinding.ItemAlarmBinding
import com.sw.wordgarden.domain.entity.AlarmEntity

class AlarmAdapter(
    private val alarmListItemListener: AlarmItemListener
) : ListAdapter<AlarmEntity, AlarmAdapter.AlarmViewHolder>(DIFF_UTIL) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlarmViewHolder {
        return AlarmViewHolder(
            ItemAlarmBinding.inflate(LayoutInflater.from(parent.context), parent, false),
        )
    }

    override fun onBindViewHolder(holder: AlarmViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class AlarmViewHolder(
        private val binding: ItemAlarmBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(alarmEntity: AlarmEntity) = with(binding) {
            val date = alarmEntity.date.toString()
            val title = "${alarmEntity.creator} ${R.string.alarm_title}"
            tvAlarmData.text = date
            tvAlarmTitle.text = title

            binding.root.setOnClickListener {
                alarmListItemListener.onItemClicked(alarmEntity.quizId)
            }
        }
    }

    companion object {
        private val DIFF_UTIL = object : DiffUtil.ItemCallback<AlarmEntity>() {
            override fun areItemsTheSame(oldItem: AlarmEntity, newItem: AlarmEntity): Boolean {
                return oldItem.quizId == newItem.quizId
            }

            override fun areContentsTheSame(oldItem: AlarmEntity, newItem: AlarmEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    interface AlarmItemListener {
        fun onItemClicked(quizId: String)
    }
}


