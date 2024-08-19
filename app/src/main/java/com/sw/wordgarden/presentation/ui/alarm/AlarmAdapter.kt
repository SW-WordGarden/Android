package com.sw.wordgarden.presentation.ui.alarm

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getString
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sw.wordgarden.R
import com.sw.wordgarden.databinding.ItemAlarmBinding
import com.sw.wordgarden.domain.entity.alarm.AlarmEntity

class AlarmAdapter(
    private val alarmListItemListener: AlarmItemListener
) : ListAdapter<AlarmEntity, AlarmAdapter.AlarmViewHolder>(DIFF_UTIL) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlarmViewHolder {
        return AlarmViewHolder(
            ItemAlarmBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            parent.context
        )
    }

    override fun onBindViewHolder(holder: AlarmViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class AlarmViewHolder(
        private val binding: ItemAlarmBinding,
        private val context: Context,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(alarmEntity: AlarmEntity) = with(binding) {
            val date = alarmEntity.date.toString()
            val title = "${alarmEntity.creator} ${getString(context, R.string.alarm_check_quiz)}"
            tvAlarmData.text = date
            tvAlarmTitle.text = title

            binding.root.setOnClickListener {
                alarmListItemListener.onItemClicked(alarmEntity)
            }
        }
    }

    companion object {
        private val DIFF_UTIL = object : DiffUtil.ItemCallback<AlarmEntity>() {
            override fun areItemsTheSame(oldItem: AlarmEntity, newItem: AlarmEntity): Boolean {
                return oldItem.sqId == newItem.sqId
            }

            override fun areContentsTheSame(oldItem: AlarmEntity, newItem: AlarmEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    interface AlarmItemListener {
        fun onItemClicked(alarmEntity: AlarmEntity)
    }
}


