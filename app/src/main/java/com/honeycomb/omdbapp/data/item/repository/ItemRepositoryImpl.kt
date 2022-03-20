package com.honeycomb.omdbapp.data.item.repository

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.honeycomb.omdbapp.data.item.remote.api.ItemApi
import com.honeycomb.omdbapp.data.item.remote.dto.ItemResponse
import com.honeycomb.omdbapp.domain.common.BaseResult
import com.honeycomb.omdbapp.domain.item.entity.ItemEntity
import com.honeycomb.omdbapp.domain.item.ItemRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ItemRepositoryImpl @Inject constructor(private val itemApi: ItemApi) :
    ItemRepository {
    override suspend fun getItem(searchText:String): Flow<BaseResult<ItemEntity, ItemResponse>> {
        return flow {
            val response = itemApi.getItem(searchText)
            if (response.isSuccessful){
                val body = response.body()!!

                emit(BaseResult.Success(ItemEntity(body.title,body.released,body.runtime,body.genre,body.plot,body.poster,body.imdbRating)))
            }else{
                val type = object : TypeToken<ItemResponse>(){}.type
                val err = Gson().fromJson<ItemResponse>(response.errorBody()!!.charStream(), type)!!
                emit(BaseResult.Error(err))
            }
        }
    }
}