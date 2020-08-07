package com.jeancsanchez.reddit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*

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
            txtTitle.text = post.title

            txtAuthor.apply {
                val dateFormat = SimpleDateFormat("dd/MM/yy 'às' HH:MM", Locale.getDefault())
                text = context.getString(
                    R.string.label_post_author,
                    post.author,
                    dateFormat.format(post.createdAt)
                )
            }

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