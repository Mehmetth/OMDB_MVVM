package com.honeycomb.omdbapp.domain.search.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SearchEntity(
    var Search: List<SearchItemEntity>
): Parcelable
