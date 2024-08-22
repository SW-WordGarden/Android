package com.sw.wordgarden.presentation.ui.mypage.mypage

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sw.wordgarden.R
import com.sw.wordgarden.databinding.ItemMyFriendsBinding
import com.sw.wordgarden.domain.entity.user.FriendEntity
import com.sw.wordgarden.presentation.util.ImageConverter.stringToByteArray

class MyPageFriendAdapter(
    private val context: Context,
    private val friendItemListener: FriendItemListener
) : ListAdapter<FriendEntity, MyPageFriendAdapter.MyPageFriendViewHolder>(DIFF_UTIL) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyPageFriendViewHolder {
        return MyPageFriendViewHolder(
            ItemMyFriendsBinding.inflate(LayoutInflater.from(parent.context), parent, false),
        )
    }

    override fun onBindViewHolder(holder: MyPageFriendViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class MyPageFriendViewHolder(
        private val binding: ItemMyFriendsBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: FriendEntity) = with(binding) {
            val thumbnail = stringToByteArray(item.profileImg ?: "")
            Glide.with(context)
                .load(thumbnail)
                .error(R.drawable.img_default_thumbnail)
                .fallback(R.drawable.img_default_thumbnail)
                .into(ivFriendThumbnailItem)

            tvFriendNameItem.text = item.nickname

            root.setOnClickListener {
                friendItemListener.onItemClicked(item)
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

    interface FriendItemListener {
        fun onItemClicked(item: FriendEntity)
    }
}


