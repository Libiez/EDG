package com.edg.domain.di

import com.edg.domain.usecase.favProducts.FavProductsUseCaseImpl
import com.edg.domain.usecase.favProducts.GetFavProductsUseCase
import com.edg.domain.usecase.getproducts.GetProductsUseCase
import com.edg.domain.usecase.getproducts.GetProductsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class GetProductsUseCaseModule {
    @Binds
    @Singleton
    internal abstract fun bindGetProductsUseCase(useCaseImpl: GetProductsUseCaseImpl)
    : GetProductsUseCase

    @Binds
    @Singleton
    internal abstract fun bindFavProductsUseCase(useCaseImpl: FavProductsUseCaseImpl)
            : GetFavProductsUseCase

}
