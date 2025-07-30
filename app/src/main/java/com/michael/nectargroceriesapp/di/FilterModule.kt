package com.michael.nectargroceriesapp.di

import com.michael.nectargroceriesapp.domain.repository.ProductRepository
import com.michael.nectargroceriesapp.domain.usecase.filter.FilterProductsByCategory
import com.michael.nectargroceriesapp.domain.usecase.filter.FilterProductsByPrice
import com.michael.nectargroceriesapp.domain.usecase.filter.FilterProductsByReview
import com.michael.nectargroceriesapp.domain.usecase.filter.FilterProductsBySearchQuery
import com.michael.nectargroceriesapp.domain.usecase.filter.FilterUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FilterModule {
    @Provides
    @Singleton
    fun provideFilterByCategoryUseCase(productRepository: ProductRepository): FilterProductsByCategory
            = FilterProductsByCategory(productRepository)

    @Provides
    @Singleton
    fun provideFilterByPriceUseCase(productRepository: ProductRepository): FilterProductsByPrice
            = FilterProductsByPrice(productRepository)

    @Provides
    @Singleton
    fun provideFilterByReviewUseCase(productRepository: ProductRepository): FilterProductsByReview
            = FilterProductsByReview(productRepository)

    @Provides
    @Singleton
    fun provideFilterBySearchQueryUseCase(productRepository: ProductRepository): FilterProductsBySearchQuery
            = FilterProductsBySearchQuery(productRepository)

    @Provides
    @Singleton
    fun provideFilterUseCases(
        filterProductsByPrice: FilterProductsByPrice,
        filterProductsByReview: FilterProductsByReview,
        filterProductsByCategory: FilterProductsByCategory,
        filterProductsBySearchQuery: FilterProductsBySearchQuery
    ): FilterUseCases {
        return FilterUseCases(
            filterProductsByPrice,
            filterProductsByReview,
            filterProductsByCategory,
            filterProductsBySearchQuery
        )
    }
}