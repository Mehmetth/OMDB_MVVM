package com.honeycomb.omdbapp.presentation.display

import com.honeycomb.omdbapp.domain.search.entity.SearchItemEntity

interface IItemClick {
    fun itemClicked(usersEntity: SearchItemEntity)
}