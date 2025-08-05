package com.michael.nectargroceriesapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.michael.nectargroceriesapp.domain.model.Cart
import com.michael.nectargroceriesapp.domain.model.CartWithProduct
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {

    @Transaction
    @Query("SELECT * FROM cart")
    fun getCartWithProducts(): Flow<List<CartWithProduct>>

    @Query("SELECT * FROM cart")
    fun getCart(): Flow<List<Cart>>

    @Query("SELECT * FROM cart WHERE productId = :productId")
    suspend fun getCart(productId: Int): Cart?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCart(cart: Cart)

    @Delete
    suspend fun deleteCart(cart: Cart)

    @Query("DELETE FROM cart")
    suspend fun deleteWholeCart()


    @Query("""
        SELECT SUM(price * count) 
        FROM cart
        LEFT JOIN products ON cart.productId = products.id""")
    fun getTotalPrice(): Flow<Double>
}

