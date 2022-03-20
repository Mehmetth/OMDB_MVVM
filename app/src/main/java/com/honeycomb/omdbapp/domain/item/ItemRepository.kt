package com.honeycomb.omdbapp.domain.item

import com.honeycomb.omdbapp.data.item.remote.dto.ItemResponse
import com.honeycomb.omdbapp.domain.common.BaseResult
import com.honeycomb.omdbapp.domain.item.entity.ItemEntity
import kotlinx.coroutines.flow.Flow

interface ItemRepository {
    suspend fun getItem(search:String) : Flow<BaseResult<ItemEntity, ItemResponse>>
}