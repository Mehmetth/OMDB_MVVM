package com.honeycomb.omdbapp.data.search.remote.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SearchResponse(
    @SerializedName("Search")
    var search: List<SearchItem>
):Parcelable
