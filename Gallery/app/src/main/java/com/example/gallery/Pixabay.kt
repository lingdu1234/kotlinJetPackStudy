package com.example.gallery

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class Pixabay(
    val totalHit: Int,
    val hits: Array<PhotoItem>,
    val total: Int
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Pixabay

        if (totalHit != other.totalHit) return false
        if (!hits.contentEquals(other.hits)) return false
        if (total != other.total) return false

        return true
    }

    override fun hashCode(): Int {
        var result = totalHit
        result = 31 * result + hits.contentHashCode()
        result = 31 * result + total
        return result
    }
}

@Parcelize
data class PhotoItem(
    @SerializedName("webformatURL") val previewUrl: String,
    @SerializedName("id") val photoId: Int,
    @SerializedName("largeImageURL") val fullUrl: String,
    @SerializedName("webformatWidth") val photoWidth: Int,
    @SerializedName("webformatHeight") val photoHeight: Int,
    @SerializedName("user") val photoUser: String,
    @SerializedName("likes") val photoLikes: Int,
    @SerializedName("favorites") val photoFavorites: Int,
) : Parcelable
/*
            "webformatWidth":640,
            "webformatHeight":426,
            "favorites":1044,
            "likes":1205,
            "comments":273,
            "user_id":1564471,
            "user":"anncapictures",
                        "imageWidth":6000,
            "imageHeight":4000,

 */
