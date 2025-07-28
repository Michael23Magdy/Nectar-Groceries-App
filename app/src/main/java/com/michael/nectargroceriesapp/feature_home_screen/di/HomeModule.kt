package com.michael.nectargroceriesapp.feature_home_screen.di

import com.michael.nectargroceriesapp.core.domain.repository.CategoryRepository
import com.michael.nectargroceriesapp.core.domain.repository.ProductRepository
import com.michael.nectargroceriesapp.feature_home_screen.presentation.HomeViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class HomeModule {
    @Provides
    fun provideHomeViewModel(
        productRepository: ProductRepository,
        categoryRepository: CategoryRepository
    ): HomeViewModel {
        return HomeViewModel(productRepository, categoryRepository)
    }

}