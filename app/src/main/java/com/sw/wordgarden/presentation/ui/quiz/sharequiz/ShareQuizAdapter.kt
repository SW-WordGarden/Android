package com.sw.wordgarden.presentation.ui.quiz.sharequiz

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sw.wordgarden.R
import com.sw.wordgarden.databinding.ItemFriendsShareBinding
import com.sw.wordgarden.presentation.model.FriendModel
import java.util.Locale

@SuppressLint("NotifyDataSetChanged")
class ShareQuizAdapter(
    private val friendItemListener: FriendItemListener
) : ListAdapter<FriendModel, ShareQuizAdapter.ShareQuizViewHolder>(DIFF_UTIL), Filterable {

    private var friendList: List<FriendModel> = emptyList()
    private var filteredFriendList: List<FriendModel> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShareQuizViewHolder {
        return ShareQuizViewHolder(
            ItemFriendsShareBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            parent.context
        )
    }

    override fun onBindViewHolder(holder: ShareQuizViewHolder, position: Int) {
        holder.bind(filteredFriendList[position])
    }

    override fun getItemCount(): Int {
        return filteredFriendList.size
    }

    override fun submitList(list: List<FriendModel>?) {
        friendList = list ?: emptyList()
        filteredFriendList = friendList
        notifyDataSetChanged()
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val query = constraint?.toString()?.lowercase(Locale.getDefault()) ?: ""
                filteredFriendList = if (query.isEmpty()) {
                    friendList
                } else {
                    friendList.filter {
                        it.nickname.lowercase(Locale.getDefault()).contains(query)
                    }
                }
                return FilterResults().apply { values = filteredFriendList }
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredFriendList = (results?.values as List<*>).filterIsInstance<FriendModel>()
                notifyDataSetChanged()
            }
        }
    }

    inner class ShareQuizViewHolder(
        private val binding: ItemFriendsShareBinding,
        private val context: Context
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: FriendModel) = with(binding) {
            Glide.with(context)
                .load(item.thumbnail)
                .error(R.drawable.bg_filled_indicator)
                .fallback(R.drawable.bg_filled_indicator)
                .into(ivFriendShareThumbnail)

            tvFriendShareNickname.text = item.nickname

            root.setOnClickListener {
                friendItemListener.onItemClicked(item)
            }
        }
    }

    companion object {
        private val DIFF_UTIL = object : DiffUtil.ItemCallback<FriendModel>() {
            override fun areItemsTheSame(oldItem: FriendModel, newItem: FriendModel): Boolean {
                return oldItem.uid == newItem.uid
            }

            override fun areContentsTheSame(oldItem: FriendModel, newItem: FriendModel): Boolean {
                return oldItem == newItem
            }
        }
    }

    interface FriendItemListener {
        fun onItemClicked(item: FriendModel)
    }
}


