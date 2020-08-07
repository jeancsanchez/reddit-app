package com.jeancsanchez.reddit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.content_scrolling.*

class PostDetailActivity : AppCompatActivity() {
    lateinit var post: Post

    companion object {
        const val INTENT_POST_DETAIL = "INTENT_POST_DETAIL"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_detail)
        setSupportActionBar(findViewById(R.id.toolbar))
        intent?.apply { post = getParcelableExtra(INTENT_POST_DETAIL)!! } ?: finish()
        txtPostDetailTitle.text = post.title
    }
}