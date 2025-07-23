package com.michael.nectargroceriesapp.core.di

import android.content.Context
import androidx.room.Room
import com.michael.nectargroceriesapp.core.data.local.CategoryDao
import com.michael.nectargroceriesapp.core.data.local.GroceriesDatabase
import com.michael.nectargroceriesapp.core.data.local.ProductDao
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
        ).build()
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

}