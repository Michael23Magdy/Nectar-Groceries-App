package com.michael.nectargroceriesapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.michael.nectargroceriesapp.data.local.converters.MapConverter
import com.michael.nectargroceriesapp.domain.model.Cart
import com.michael.nectargroceriesapp.domain.model.Category
import com.michael.nectargroceriesapp.domain.model.Product

@Database(
    entities = [Category::class, Product::class, Cart::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(MapConverter::class)
abstract class GroceriesDatabase: RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
    abstract fun productDao(): ProductDao
    abstract fun cartDao(): CartDao


    companion object {
        const val DATABASE_NAME = "groceries_db"
    }
}