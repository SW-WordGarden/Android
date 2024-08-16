package com.sw.wordgarden.presentation.ui.quiz.resultquiz

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sw.wordgarden.R
import com.sw.wordgarden.databinding.ItemMakingQuizIndicatorBinding
import com.sw.wordgarden.domain.entity.QuestionResultEntity

class IndicatorAdapter(
    private val itemCount: Int,
    private val result: List<QuestionResultEntity>,
    private val onClick: (position: Int) -> Unit
) : RecyclerView.Adapter<IndicatorAdapter.IndicatorViewHolder>() {

    private var selectedPosition = 0

    fun updateSelectedPosition(position: Int) {
        val previousPosition = selectedPosition
        selectedPosition = position
        notifyItemChanged(previousPosition)
        notifyItemChanged(selectedPosition)
    }

    override fun getItemCount(): Int = itemCount

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IndicatorViewHolder {
        val binding = ItemMakingQuizIndicatorBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return IndicatorViewHolder(binding, onClick)
    }

    override fun onBindViewHolder(holder: IndicatorViewHolder, position: Int) {
        holder.bind(
            position,
            isSelected = position == selectedPosition,
            isCorrect = result[position].correct ?: false
        )
    }

    class IndicatorViewHolder(
        private val binding: ItemMakingQuizIndicatorBinding,
        private val onClick: (position: Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                onClick(bindingAdapterPosition)
            }
        }

        fun bind(position: Int, isSelected: Boolean, isCorrect: Boolean) {
            val newPosition = position + 1
            binding.apply {
                tvItemMakingQuizIndicator.text = newPosition.toString()

                val backgroundResource = when {
                    isSelected && isCorrect -> R.drawable.bg_selected_indicator_correct
                    !isSelected && isCorrect -> R.drawable.bg_unselected_indicator_correct
                    isSelected && !isCorrect -> R.drawable.bg_selected_indicator_incorrect
                    else -> R.drawable.bg_unselected_indicator_incorrect
                }

                tvItemMakingQuizIndicator.setBackgroundResource(backgroundResource)

                val textColor = if (isCorrect) Color.BLACK else Color.WHITE
                tvItemMakingQuizIndicator.setTextColor(textColor)
            }
        }
    }
}
