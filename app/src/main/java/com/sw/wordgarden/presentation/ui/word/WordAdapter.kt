package com.sw.wordgarden.presentation.ui.word

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sw.wordgarden.databinding.ItemWordBinding
import com.sw.wordgarden.presentation.model.WordModel

class WordAdapter( private val onItemClick : (WordModel) -> Unit) : ListAdapter<WordModel, WordAdapter.ItemViewHolder>(
    object : DiffUtil.ItemCallback<WordModel>(){
        override fun areItemsTheSame(oldItem: WordModel, newItem: WordModel): Boolean {
            return oldItem == newItem
        }
        override fun areContentsTheSame(oldItem: WordModel, newItem: WordModel): Boolean {
            return oldItem == newItem
        }
    }
) {
    inner class ItemViewHolder(
        private val binding:ItemWordBinding,
        private val onItemClick: (WordModel) -> Unit
    ):RecyclerView.ViewHolder(binding.root){
        fun bind(wordData:WordModel) = with(binding){
            wordTitle.text = wordData.title
            wordContents.text = wordData.description
            clWordItem.setOnClickListener {
                onItemClick(wordData)
            }
        }
    }
    override fun onBindViewHolder(holder: ItemViewHolder, position:Int){
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(ItemWordBinding.inflate(LayoutInflater.from(parent.context), parent, false), onItemClick)
    }

}