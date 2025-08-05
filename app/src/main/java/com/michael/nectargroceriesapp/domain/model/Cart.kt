package com.michael.nectargroceriesapp.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "cart",
    foreignKeys = [ForeignKey(
        entity = Product::class,
        parentColumns = ["id"],
        childColumns = ["productId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class Cart(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "cartId")
    val id: Int = 0,

    @ColumnInfo(name = "productId")
    val productId: Int,

    @ColumnInfo(name = "count")
    val count: Int
)
