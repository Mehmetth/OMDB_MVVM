package com.honeycomb.omdbapp.domain.item.usecase

import com.honeycomb.omdbapp.data.item.remote.dto.ItemResponse
import com.honeycomb.omdbapp.data.search.remote.dto.SearchResponse
import com.honeycomb.omdbapp.domain.SearchRepository
import com.honeycomb.omdbapp.domain.common.BaseResult
import com.honeycomb.omdbapp.domain.item.ItemRepository
import com.honeycomb.omdbapp.domain.item.entity.ItemEntity
import com.honeycomb.omdbapp.domain.search.entity.SearchEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ItemUseCase @Inject constructor(private val itemRepository: ItemRepository) {
    suspend fun execute(searchText:String): Flow<BaseResult<ItemEntity, ItemResponse>> {
        return itemRepository.getItem(searchText)
    }
}