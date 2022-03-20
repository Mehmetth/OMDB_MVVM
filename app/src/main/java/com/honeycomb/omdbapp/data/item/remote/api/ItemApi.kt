package com.honeycomb.omdbapp.data.item.remote.api

import com.honeycomb.omdbapp.BuildConfig
import com.honeycomb.omdbapp.data.item.remote.dto.ItemResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ItemApi{
    @GET("?apikey=${BuildConfig.API_KEY}")
    suspend fun getItem(
        @Query("i", encoded = true) i:String) : Response<ItemResponse>
}