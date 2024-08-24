package com.sw.wordgarden.presentation.ui.quiz.solvequiz

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.sw.wordgarden.R
import com.sw.wordgarden.databinding.ItemQuizIndicatorBinding

class IndicatorAdapter(
    private val itemCount: Int,
    private val onClick: (position: Int) -> Unit
) : RecyclerView.Adapter<IndicatorAdapter.IndicatorViewHolder>() {

    private var selectedPosition = 0
    private val filledPositions = mutableSetOf<Int>()

    fun updateSelectedPosition(position: Int) {
        val previousPosition = selectedPosition
        selectedPosition = position
        notifyItemChanged(previousPosition)
        notifyItemChanged(selectedPosition)
    }

    fun markAsFilled(position: Int) {
        if (!filledPositions.contains(position)) {
            filledPositions.add(position)
            notifyItemChanged(position)
        }
    }

    fun markAsEmpty(position: Int) {
        if (filledPositions.contains(position)) {
            filledPositions.remove(position)
            notifyItemChanged(position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IndicatorViewHolder {
        val binding = ItemQuizIndicatorBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return IndicatorViewHolder(binding, onClick)
    }

    override fun getItemCount(): Int = itemCount

    override fun onBindViewHolder(holder: IndicatorViewHolder, position: Int) {
        holder.bind(position, position == selectedPosition, filledPositions.contains(position))
    }

    class IndicatorViewHolder(
        private val binding: ItemQuizIndicatorBinding,
        private val onClick: (position: Int) -> Unit
    ) : ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                onClick(bindingAdapterPosition)
            }
        }

        fun bind(position: Int, isSelected: Boolean, isFilled: Boolean) {
            val newPosition = position + 1
            binding.apply {
                tvItemMakingQuizIndicator.text = newPosition.toString()

                when {
                    isSelected -> {
                        tvItemMakingQuizIndicator.setBackgroundResource(R.drawable.bg_circle_selected_indicator)
                        tvItemMakingQuizIndicator.setTextColor(Color.WHITE)

                    }
                    isFilled ->  {
                        tvItemMakingQuizIndicator.setBackgroundResource(R.drawable.bg_circle_filled_indicator)
                        tvItemMakingQuizIndicator.setTextColor(Color.BLACK)
                    }
                    else -> {
                        tvItemMakingQuizIndicator.setBackgroundResource(R.drawable.bg_circle_unselected_indicator)
                        tvItemMakingQuizIndicator.setTextColor(Color.BLACK)
                    }
                }
            }
        }
    }
}