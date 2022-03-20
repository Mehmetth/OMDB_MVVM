package com.honeycomb.omdbapp.data.item

import com.honeycomb.omdbapp.data.common.NetworkModule
import com.honeycomb.omdbapp.data.item.remote.api.ItemApi
import com.honeycomb.omdbapp.data.item.repository.ItemRepositoryImpl
import com.honeycomb.omdbapp.domain.item.ItemRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
class ItemModule{
    @Singleton
    @Provides
    fun provideProductApi(retrofit: Retrofit) : ItemApi {
        return retrofit.create(ItemApi::class.java)
    }

    @Singleton
    @Provides
    fun provideProductRepository(itemApi: ItemApi) : ItemRepository {
        return ItemRepositoryImpl(itemApi)
    }
}