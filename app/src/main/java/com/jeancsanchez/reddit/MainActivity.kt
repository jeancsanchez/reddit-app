package com.jeancsanchez.reddit

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val postAdapter by lazy { PostAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerPosts?.apply {
            adapter = postAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }

        postAdapter.postClickListener = { post ->
            val intent = Intent(this, PostDetailActivity::class.java)
            intent.putExtra(PostDetailActivity.INTENT_POST_DETAIL, post)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()

        postAdapter.postList = listOf(
            Post(
                isVideo = true,
                videoThumb = "https://i.ytimg.com/vi/_7lc9Go9O2g/hqdefault.jpg",
                title = "Video top",
                author = "Me"
            ),
            Post(
                isImage = true,
                imageUrl = "https://i.redd.it/qivn8ygjndf51.jpg",
                title = "Image top",
                author = "Me"
            ),
            Post(title = "Post top", author = "Me")
        )
    }
}