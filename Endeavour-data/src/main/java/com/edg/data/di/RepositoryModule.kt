package com.edg.data.di


import com.edg.data.repository.FavProductRepositoryImpl
import com.edg.data.repository.GetProductsRepositoryImpl
import com.edg.domain.repository.FavouriteProductsRepository
import com.edg.domain.repository.GetProductsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    internal abstract fun bindWordInfoRepository(
        repository: GetProductsRepositoryImpl): GetProductsRepository

    @Binds
    @Singleton
    internal abstract fun bindFavProductRepository(
        repository: FavProductRepositoryImpl) : FavouriteProductsRepository

}
