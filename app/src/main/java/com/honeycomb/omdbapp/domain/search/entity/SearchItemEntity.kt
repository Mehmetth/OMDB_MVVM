package com.honeycomb.omdbapp.domain.search.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SearchItemEntity(
    var title: String,
    var year: String,
    var imdbID: String,
    var type: String,
    var poster: String
): Parcelable