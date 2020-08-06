package com.jeancsanchez.reddit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import java.util.*
import java.util.concurrent.TimeUnit

class PostAdapter : RecyclerView.Adapter<PostAdapter.ViewHolder>() {
    var postList: List<Post> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(postList[position])
    }

    override fun getItemCount(): Int = postList.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val txtTitle = view.rootView.findViewById<TextView>(R.id.txtPostTitle)
        private val txtAuthor = view.rootView.findViewById<TextView>(R.id.txtPostAuthorName)
        private val imgPost = view.rootView.findViewById<ImageView>(R.id.imgPost)
        private val imgPlayVideo = view.rootView.findViewById<ImageView>(R.id.imgPlayVideoPost)

        fun bind(post: Post) {
            val today = Date()
            val difference = today.time - post.createdAt.time
            val seconds = TimeUnit.MILLISECONDS.convert(difference, TimeUnit.SECONDS)
            val minutes = TimeUnit.SECONDS.convert(seconds, TimeUnit.MINUTES)
            val hours = TimeUnit.MINUTES.convert(seconds, TimeUnit.HOURS)
            val days = TimeUnit.HOURS.convert(hours, TimeUnit.DAYS)
            var datePeriod = "$days days"

            if (days < 1) {
                if (hours < 60) {
                    datePeriod = "$hours hours"
                    if (minutes < 60) {
                        datePeriod = "$minutes minutes"
                        if (seconds < 60) {
                            datePeriod = "$seconds seconds"
                        }
                    }
                }
            }

            txtAuthor.text =
                txtAuthor.context.getString(R.string.post_author, post.author, datePeriod)
            txtTitle.text = post.title

            if (post.hasMedia) {
                imgPost.visibility = View.VISIBLE
                val imgUrl: String? = if (post.isVideo) {
                    imgPlayVideo.visibility = View.VISIBLE
                    post.videoThumb
                } else {
                    imgPlayVideo.visibility = View.GONE
                    post.imageUrl
                }
                imgUrl?.let {
                    Picasso.get()
                        .load(it)
                        .fit()
                        .placeholder(R.drawable.placeholder)
                        .into(imgPost)
                }
            } else {
                imgPost.visibility = View.GONE
                imgPlayVideo.visibility = View.GONE
            }
        }
    }
}