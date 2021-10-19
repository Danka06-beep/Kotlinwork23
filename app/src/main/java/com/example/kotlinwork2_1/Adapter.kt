package com.example.kotlinwork2_1

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.post.view.*

class Adapter: RecyclerView.Adapter<ViewHolder>() {

    var items: ArrayList<Post> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return PostViewHolder(
            LayoutInflater.from(
                parent.context
            ).inflate(
                R.layout.post, parent, false
            )
        )
    }
    fun getIteanList(): List<Post> {
        return items
    }
    override fun getItemCount(): Int {
        return items.size
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (holder) {
            is PostViewHolder -> {
                holder.bind(items[position], position)
            }
        }
    }
    fun submiDataList(blockList: ArrayList<Post>) {
        items = blockList
    }
    inner class PostViewHolder
    constructor(itemView: View) : ViewHolder(itemView) {
        val authorxt = itemView.authorxt
        val txt = itemView.txt
        val likeTxt = itemView.likeTxt
        val commentTxt = itemView.commentTxt
        val shareTxt = itemView.shareTxt
        val datetxt = itemView.datetxt
        val likeImgBtn = itemView.likeImgBtn
        val coordBtn = itemView.coordBtn
        val postImage = itemView.postImage
        val repostImgAutor = itemView.repostImgAutor
        val repostDateText = itemView.repostDateText
        val repostAutorText = itemView.repostAutorText
        val typePost = itemView.typePost
        val imageHide = itemView.imageHide

        fun bind(post: Post, position: Int) {
            if (post.hidePost) {
                items.remove(post)
            }
            val requesoption = RequestOptions().placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
            when (post.type) {
                Types.YoutubeVideo -> {
                    txt.visibility = View.GONE
                    postImage.visibility = View.VISIBLE
                    postImage.setImageResource(R.drawable.ic_baseline_play_arrow_24)
                    postImage.setOnClickListener {
                        val intent = Intent().apply {
                            action = Intent.ACTION_VIEW
                            data = Uri.parse(post.txt)
                        }
                        itemView.context.startActivity(intent)
                    }
                    typePost.setText(R.string.Youtube_video)
                }
                Types.SponsoredPosts -> {
                    txt.visibility = View.GONE
                    postImage.visibility = View.VISIBLE
                    Glide.with(itemView.context)
                        .applyDefaultRequestOptions(requesoption)
                        .load(post.txt)
                        .into(postImage)
                    postImage.setOnClickListener {
                        val intent = Intent().apply {
                            action = Intent.ACTION_VIEW
                            data = Uri.parse(post.url)
                        }
                        itemView.context.startActivity(intent)
                    }
                    typePost.setText(R.string.Sponsored_posts)
                }
                Types.Reposts -> {
                    repostImgAutor.visibility = View.VISIBLE
                    repostDateText.visibility = View.VISIBLE
                    repostAutorText.visibility = View.VISIBLE

                    repostDateText.text = post.dateRepost?.toString()
                    repostAutorText.text = post.autorRepost
                    repostDateText.text = dateToString(post)
                    typePost.setText(R.string.Reposts)
                }
            }
            likeImgBtn.setOnClickListener {
                if (post.like) {
                    post.like = false
                    post.likeTxt -= 1
                    likeImgBtn.setImageResource(R.drawable.ic_baseline_favorite_disabled)
                    likeTxt.setTextColor(Color.BLACK)
                    likeTxt.text = post.likeTxt.toString()
                } else {
                    post.like = true
                    post.likeTxt += 1
                    likeImgBtn.setImageResource(R.drawable.ic_baseline_favorite_24)
                    likeTxt.setTextColor(Color.RED)
                    likeTxt.text = post.likeTxt.toString()
                }
            }
            authorxt.text = post.author
            if (post.likeTxt > 0) {
                likeTxt.text = post.likeTxt.toString()
            } else {
                likeTxt.text = ""
            }
            if (post.commentTxt > 0) {
                commentTxt.text = post.comment.toString()
            } else {
                commentTxt.text = ""
            }
            if (post.shareTxt > 0) {
                shareTxt.text = post.share.toString()
            } else {
                shareTxt.text = ""
            }
            datetxt.text = dateToString(post)

            coordBtn.setOnClickListener {
                val (lat, lng) = items[position].coordinates
                val geoUri = Uri.parse("geo:$lat,$lng")
                val intent = Intent().apply {
                    action = Intent.ACTION_VIEW
                    data = geoUri
                }
                itemView.context.startActivity(intent)
            }
            imageHide.setOnClickListener {
                post.hidePost = true
                items.remove(post)
                notifyDataSetChanged()
            }
        }
        private fun dateToString(post: Post): String {
            val publishedAgo = (System.currentTimeMillis() - post.data) / 1000
            val toMin = if (publishedAgo > 3599) {
                publishedAgo / 3600
            } else {
                publishedAgo / 60
            }
            return when (publishedAgo) {
                in 0..59 -> "менее минуты назад"
                in 60..179 -> "минуту назад"
                in 180..299 -> "$toMin минуты назад"
                in 300..3599 -> "$toMin минут назад"
                in 3600..17999 -> "$toMin часа назад"
                else -> "$toMin часов назад "
            }
        }
    }
}