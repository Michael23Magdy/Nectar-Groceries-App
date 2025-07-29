package com.michael.nectargroceriesapp.core.di

import android.content.Context
import androidx.room.Room
import com.michael.nectargroceriesapp.core.data.local.CategoryDao
import com.michael.nectargroceriesapp.core.data.local.GroceriesDatabase
import com.michael.nectargroceriesapp.core.data.local.ProductDao
import com.michael.nectargroceriesapp.core.data.repository.CategoryRepositoryImpl
import com.michael.nectargroceriesapp.core.data.repository.ProductRepositoryImpl
import com.michael.nectargroceriesapp.core.domain.repository.CategoryRepository
import com.michael.nectargroceriesapp.core.domain.repository.ProductRepository
import com.michael.nectargroceriesapp.core.domain.usecase.CoreUseCases
import com.michael.nectargroceriesapp.core.domain.usecase.GetCategories
import com.michael.nectargroceriesapp.core.domain.usecase.GetProduct
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CoreModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): GroceriesDatabase {
        return Room.databaseBuilder(
            context,
            GroceriesDatabase::class.java,
            GroceriesDatabase.DATABASE_NAME
        ).createFromAsset("database/groceries.db").build()
    }

    @Provides
    @Singleton
    fun provideCategoryDao(db: GroceriesDatabase): CategoryDao {
        return db.categoryDao()
    }

    @Provides
    @Singleton
    fun provideProductDao(db: GroceriesDatabase): ProductDao {
        return db.productDao()
    }

    @Provides
    @Singleton
    fun provideCategoryRepository(categoryDao: CategoryDao): CategoryRepository {
        return CategoryRepositoryImpl(categoryDao)
    }

    @Provides
    @Singleton
    fun provideProductRepository(productDao: ProductDao): ProductRepository {
        return ProductRepositoryImpl(productDao)
    }

    @Provides
    @Singleton
    fun provideGetCategories(categoryRepository: CategoryRepository): GetCategories {
        return GetCategories(categoryRepository)
    }

    @Provides
    @Singleton
    fun provideGetProduct(productDao: ProductDao): GetProduct {
        return GetProduct(productDao)
    }

    @Provides
    @Singleton
    fun provideCoreUseCases(
        getCategories: GetCategories,
        getProduct: GetProduct
    ): CoreUseCases = CoreUseCases(
        getCategories = getCategories,
        getProduct = getProduct
    )
}