package com.honeycomb.omdbapp.domain.search.usecase

import com.honeycomb.omdbapp.data.search.remote.dto.SearchResponse
import com.honeycomb.omdbapp.domain.SearchRepository
import com.honeycomb.omdbapp.domain.common.BaseResult
import com.honeycomb.omdbapp.domain.search.entity.SearchEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchUseCase @Inject constructor(private val searchRepository: SearchRepository) {
    suspend fun execute(searchText:String): Flow<BaseResult<SearchEntity, SearchResponse>> {
        return searchRepository.getSearchRepo(searchText)
    }
}