package com.jeancsanchez.reddit

import java.util.*

data class Post(
    val isVideo: Boolean = false,
    val isImage: Boolean = false,
    val isLink: Boolean = false,
    val videoThumb: String? = null,
    val imageUrl: String? = null,
    val link: String? = null,
    val title: String = "",
    val author: String = "",
    val createdAt: Date = Date()
) {

    val hasMedia: Boolean
        get() {
            return imageUrl != null || videoThumb !== null
        }
}