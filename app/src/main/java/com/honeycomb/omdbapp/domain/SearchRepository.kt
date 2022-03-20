package com.honeycomb.omdbapp.domain

import com.honeycomb.omdbapp.data.search.remote.dto.SearchResponse
import com.honeycomb.omdbapp.domain.common.BaseResult
import com.honeycomb.omdbapp.domain.search.entity.SearchEntity
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    suspend fun getSearchRepo(userName:String) : Flow<BaseResult<SearchEntity, SearchResponse>>
}