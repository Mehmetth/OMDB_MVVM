package com.honeycomb.omdbapp.data.search.remote.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SearchItem(
    @SerializedName("Title")
    var title: String,
    @SerializedName("Year")
    var year: String,
    @SerializedName("imdbID")
    var imdbID: String,
    @SerializedName("Type")
    var type: String,
    @SerializedName("Poster")
    var poster: String
): Parcelable
