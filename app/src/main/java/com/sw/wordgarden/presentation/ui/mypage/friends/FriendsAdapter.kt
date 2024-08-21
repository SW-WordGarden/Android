package com.sw.wordgarden.presentation.ui.mypage.friends

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sw.wordgarden.R
import com.sw.wordgarden.databinding.ItemMyFriendsBigBinding
import com.sw.wordgarden.domain.entity.user.FriendEntity
import com.sw.wordgarden.presentation.util.ImageConverter.stringToByteArray

class FriendsAdapter(
    private val friendsListItemListener: FriendsItemListener
) : ListAdapter<FriendEntity, FriendsAdapter.FriendsViewHolder>(DIFF_UTIL) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendsViewHolder {
        return FriendsViewHolder(
            parent.context,
            ItemMyFriendsBigBinding.inflate(LayoutInflater.from(parent.context), parent, false),
        )
    }

    override fun onBindViewHolder(holder: FriendsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class FriendsViewHolder(
        private val context: Context,
        private val binding: ItemMyFriendsBigBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(friend: FriendEntity) = with(binding) {
            val thumbnail = stringToByteArray(friend.profileImg ?: "")
            Glide.with(context)
                .load(thumbnail)
                .error(R.drawable.img_default_thumbnail)
                .fallback(R.drawable.img_default_thumbnail)
                .into(ivFriendThumbnail)

            tvFriendNickname.text = friend.nickname

            ivFriendDelete.setOnClickListener {
                friendsListItemListener.onItemDeleteClick(friend.uid)
            }

            ivFriendReport.setOnClickListener {
                friendsListItemListener.onItemReportClick(friend.uid)
            }
        }
    }

    companion object {
        private val DIFF_UTIL = object : DiffUtil.ItemCallback<FriendEntity>() {
            override fun areItemsTheSame(oldItem: FriendEntity, newItem: FriendEntity): Boolean {
                return oldItem.uid == newItem.uid
            }

            override fun areContentsTheSame(oldItem: FriendEntity, newItem: FriendEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    interface FriendsItemListener {
        fun onItemDeleteClick(friendUid: String?)
        fun onItemReportClick(friendUid: String?)
    }
}


