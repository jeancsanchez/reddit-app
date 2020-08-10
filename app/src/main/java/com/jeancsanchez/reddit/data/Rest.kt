package com.jeancsanchez.reddit.data

import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Rest {
    fun getAPI(): RedditAPI {
        return Retrofit.Builder()
            .baseUrl("http://www.reddit.com/")
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .client(OkHttpClient().newBuilder().build())
            .build()
            .create(RedditAPI::class.java)
    }
}