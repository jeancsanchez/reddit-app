package com.jeancsanchez.reddit.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RedditAPI {

    @GET("r/{theme}.json")
    fun getPostsByTheme(@Path("theme") theme: String): Call<ResponseWrapper>
}