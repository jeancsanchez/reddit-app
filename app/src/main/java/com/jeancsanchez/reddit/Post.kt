package com.jeancsanchez.reddit

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.util.*

data class Post(
    @SerializedName("is_video")
    val isVideo: Boolean = false,

    val isImage: Boolean = false,
    val isLink: Boolean = false,

    @SerializedName("thumbnail")
    val videoThumb: String? = null,
    val imageUrl: String? = null,
    val link: String? = null,
    val title: String = "",
    val author: String = "",
    val createdAt: Date = Date()
) : Parcelable {

    val hasMedia: Boolean
        get() {
            return imageUrl != null || videoThumb !== null
        }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Post> = object : Parcelable.Creator<Post> {
            override fun createFromParcel(source: Parcel): Post = Post(source)
            override fun newArray(size: Int): Array<Post?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel) : this(
        1 == source.readInt(),
        1 == source.readInt(),
        1 == source.readInt(),
        source.readString(),
        source.readString(),
        source.readString(),
        source.readString() ?: "",
        source.readString() ?: "",
        source.readSerializable() as Date
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt((if (isVideo) 1 else 0))
        writeInt((if (isImage) 1 else 0))
        writeInt((if (isLink) 1 else 0))
        writeString(videoThumb)
        writeString(imageUrl)
        writeString(link)
        writeString(title)
        writeString(author)
        writeSerializable(createdAt)
    }
}
