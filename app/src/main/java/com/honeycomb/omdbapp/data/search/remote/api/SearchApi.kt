package com.honeycomb.omdbapp.data.search.remote.api

import com.honeycomb.omdbapp.BuildConfig
import com.honeycomb.omdbapp.data.search.remote.dto.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SearchApi {
    @GET("?apikey=${BuildConfig.API_KEY}")
    suspend fun getDatas(
        @Query("s", encoded = true) s:String) : Response<SearchResponse>
}