package com.honeycomb.omdbapp.data.search

import android.util.Log
import com.honeycomb.omdbapp.data.common.NetworkModule
import com.honeycomb.omdbapp.data.search.remote.api.SearchApi
import com.honeycomb.omdbapp.data.search.repository.SearchRepositoryImpl
import com.honeycomb.omdbapp.domain.SearchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
class SearchModule {
    @Singleton
    @Provides
    fun provideProductApi(retrofit: Retrofit) : SearchApi {
        return retrofit.create(SearchApi::class.java)
    }

    @Singleton
    @Provides
    fun provideProductRepository(usersApi: SearchApi) : SearchRepository {
        return SearchRepositoryImpl(usersApi)
    }
}