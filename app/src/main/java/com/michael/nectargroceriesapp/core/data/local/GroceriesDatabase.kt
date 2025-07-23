package com.michael.nectargroceriesapp.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.michael.nectargroceriesapp.core.data.local.converters.MapConverter
import com.michael.nectargroceriesapp.core.domain.model.Category
import com.michael.nectargroceriesapp.core.domain.model.Product

@Database(
    entities = [Category::class, Product::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(MapConverter::class)
abstract class GroceriesDatabase: RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
    abstract fun productDao(): ProductDao

    companion object {
        const val DATABASE_NAME = "groceries_db"
    }
}