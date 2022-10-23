package com.edg.domain.di

import com.edg.domain.usecase.GetProductsUseCase
import com.edg.domain.usecase.GetProductsUseCaseImpl
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
    internal abstract fun bindGetProductsUseCase(useCaseImpl: GetProductsUseCaseImpl): GetProductsUseCase
}
