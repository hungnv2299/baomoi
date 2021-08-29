package com.hung.baomoi.video.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hung.baomoi.R
import com.hung.baomoi.video.data.model.Video
import kotlinx.android.synthetic.main.item_video.view.*


class VideoAdapter : RecyclerView.Adapter<VideoAdapter.VideoViewHolder>() {
    inner class VideoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val diffCallBack = object : DiffUtil.ItemCallback<Video>() {
        override fun areItemsTheSame(oldItem: Video, newItem: Video): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Video, newItem: Video): Boolean {
            return oldItem == newItem
        }

    }
    val diff = AsyncListDiffer(this, diffCallBack)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        return VideoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_video,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        val video = diff.currentList[position]
        holder.itemView.apply {
            Glide.with(this).load(video.urlToImage).into(ivVideoImage)
            tvSourceVideo.text = video.source
            tvPublishedAtVideo.text = video.publishedAt
            tvTitleVideo.text = video.title
            setOnClickListener {
                onItemClickListener?.let { it(video) }
            }
        }
    }

    private var onItemClickListener: ((Video) -> Unit)? = null

    fun setOnItemClickListener(listener: (Video) -> Unit) {
        onItemClickListener = listener
    }

    override fun getItemCount(): Int {
        return diff.currentList.size
    }

}