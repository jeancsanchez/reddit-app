package com.jeancsanchez.reddit.data

import com.jeancsanchez.reddit.Post

data class PostData(val data: Post)
data class Data(val children: List<PostData>)
data class ResponseWrapper(val kind: String, val data: Data)