package com.honeycomb.omdbapp.domain.item.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ItemEntity(
    var title: String,
    var released: String,
    var runtime: String,
    var genre: String,
    var plot: String,
    var poster: String,
    var imdbRating: String
): Parcelable