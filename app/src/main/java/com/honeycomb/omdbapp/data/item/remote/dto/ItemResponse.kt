package com.honeycomb.omdbapp.data.item.remote.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.honeycomb.omdbapp.data.search.remote.dto.SearchItem
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ItemResponse(
    @SerializedName("Title")
    var title: String,
    @SerializedName("Released")
    var released: String,
    @SerializedName("Runtime")
    var runtime: String,
    @SerializedName("Genre")
    var genre: String,
    @SerializedName("Plot")
    var plot: String,
    @SerializedName("Poster")
    var poster: String,
    @SerializedName("imdbRating")
    var imdbRating: String
): Parcelable
