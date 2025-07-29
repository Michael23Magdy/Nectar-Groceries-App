package com.michael.nectargroceriesapp.feature_filter.di

import com.michael.nectargroceriesapp.core.domain.repository.ProductRepository
import com.michael.nectargroceriesapp.feature_filter.domain.usecase.FilterProductsByCategory
import com.michael.nectargroceriesapp.feature_filter.domain.usecase.FilterProductsByPrice
import com.michael.nectargroceriesapp.feature_filter.domain.usecase.FilterProductsByReview
import com.michael.nectargroceriesapp.feature_filter.domain.usecase.FilterProductsBySearchQuery
import com.michael.nectargroceriesapp.feature_filter.domain.usecase.FilterUseCases
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
