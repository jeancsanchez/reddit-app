package com.jeancsanchez.reddit

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.jeancsanchez.reddit.data.ResponseWrapper
import com.jeancsanchez.reddit.data.Rest
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

        Rest.getAPI()
            .getPostsByTheme("PublicFreakout")
            .enqueue(object : Callback<ResponseWrapper> {
                override fun onResponse(
                    call: Call<ResponseWrapper>,
                    response: Response<ResponseWrapper>
                ) {
                    response.body()?.let { wrapper ->
                        postAdapter.postList = wrapper.data.children.map { it.data }
                    }
                }

                override fun onFailure(call: Call<ResponseWrapper>, t: Throwable) {
                    t.printStackTrace()
                }
            })
    }
}