package com.honeycomb.omdbapp.data.search.repository

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.honeycomb.omdbapp.data.search.remote.api.SearchApi
import com.honeycomb.omdbapp.data.search.remote.dto.SearchResponse
import com.honeycomb.omdbapp.domain.SearchRepository
import com.honeycomb.omdbapp.domain.common.BaseResult
import com.honeycomb.omdbapp.domain.search.entity.SearchEntity
import com.honeycomb.omdbapp.domain.search.entity.SearchItemEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(private val searchApi: SearchApi) :
    SearchRepository {
    override suspend fun getSearchRepo(searchText:String): Flow<BaseResult<SearchEntity, SearchResponse>> {
        return flow {
            val response = searchApi.getDatas(searchText)
            if (response.isSuccessful){
                val body = response.body()!!

                val search = mutableListOf<SearchItemEntity>()
                body.search.forEach { item ->
                    search.add(SearchItemEntity(item.title,item.year,item.imdbID,item.type,item.poster))
                }

                emit(BaseResult.Success(SearchEntity(search)))
            }else{
                val type = object : TypeToken<SearchResponse>(){}.type
                val err = Gson().fromJson<SearchResponse>(response.errorBody()!!.charStream(), type)!!
                emit(BaseResult.Error(err))
            }
        }
    }
}