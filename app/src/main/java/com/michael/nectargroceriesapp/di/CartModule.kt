package com.michael.nectargroceriesapp.di

import com.michael.nectargroceriesapp.data.local.CartDao
import com.michael.nectargroceriesapp.data.local.GroceriesDatabase
import com.michael.nectargroceriesapp.data.repository.CartRepositoryImpl
import com.michael.nectargroceriesapp.domain.repository.CartRepository
import com.michael.nectargroceriesapp.domain.usecase.cart.CartUseCases
import com.michael.nectargroceriesapp.domain.usecase.cart.DeleteCartItem
import com.michael.nectargroceriesapp.domain.usecase.cart.DeleteWholeCart
import com.michael.nectargroceriesapp.domain.usecase.cart.GetCart
import com.michael.nectargroceriesapp.domain.usecase.cart.InsertCartItem
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CartModule {
    @Provides
    @Singleton
    fun provideCartDao(db: GroceriesDatabase): CartDao {
        return db.cartDao()
    }

    @Provides
    @Singleton
    fun provideCartRepository(cartDao: CartDao): CartRepository {
        return CartRepositoryImpl(cartDao)
    }

    @Provides
    @Singleton
    fun provideGetCart(cartRepository: CartRepository): GetCart {
        return GetCart(cartRepository)
    }

    @Provides
    @Singleton
    fun provideInsertCartItem(cartRepository: CartRepository): InsertCartItem {
        return InsertCartItem(cartRepository)
    }

    @Provides
    @Singleton
    fun provideDeleteCartItem(cartRepository: CartRepository): DeleteCartItem {
        return DeleteCartItem(cartRepository)
    }

    @Provides
    @Singleton
    fun provideDeleteWholeCart(cartRepository: CartRepository): DeleteWholeCart {
        return DeleteWholeCart(cartRepository)
    }

    @Provides
    @Singleton
    fun provideCartUseCases(
        getCart: GetCart,
        deleteCartItem: DeleteCartItem,
        deleteWholeCart: DeleteWholeCart,
        insertCartItem: InsertCartItem
    ): CartUseCases = CartUseCases(
        getCart = getCart,
        deleteCartItem = deleteCartItem,
        deleteWholeCart = deleteWholeCart,
        insertCartItem = insertCartItem
    )

}