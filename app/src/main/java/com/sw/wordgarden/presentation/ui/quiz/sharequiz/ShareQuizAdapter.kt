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
import com.sw.wordgarden.domain.entity.user.UserEntity
import java.util.Locale

@SuppressLint("NotifyDataSetChanged")
class ShareQuizAdapter(
    private val friendItemListener: FriendItemListener
) : ListAdapter<UserEntity, ShareQuizAdapter.ShareQuizViewHolder>(DIFF_UTIL), Filterable {

    private var friendList: List<UserEntity> = emptyList()
    private var filteredFriendList: List<UserEntity> = emptyList()

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

    override fun submitList(list: List<UserEntity>?) {
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
                        it.uName?.lowercase(Locale.getDefault())?.contains(query) ?: false
                    }
                }
                return FilterResults().apply { values = filteredFriendList }
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredFriendList = (results?.values as List<*>).filterIsInstance<UserEntity>()
                notifyDataSetChanged()
            }
        }
    }

    inner class ShareQuizViewHolder(
        private val binding: ItemFriendsShareBinding,
        private val context: Context
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: UserEntity) = with(binding) {
            Glide.with(context)
                .load(item.uImage)
                .error(R.drawable.bg_filled_indicator)
                .fallback(R.drawable.bg_filled_indicator)
                .into(ivFriendShareThumbnail)

            tvFriendShareNickname.text = item.uName

            root.setOnClickListener {
                friendItemListener.onItemClicked(item)
            }
        }
    }

    companion object {
        private val DIFF_UTIL = object : DiffUtil.ItemCallback<UserEntity>() {
            override fun areItemsTheSame(oldItem: UserEntity, newItem: UserEntity): Boolean {
                return oldItem.uid == newItem.uid
            }

            override fun areContentsTheSame(oldItem: UserEntity, newItem: UserEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    interface FriendItemListener {
        fun onItemClicked(item: UserEntity)
    }
}


