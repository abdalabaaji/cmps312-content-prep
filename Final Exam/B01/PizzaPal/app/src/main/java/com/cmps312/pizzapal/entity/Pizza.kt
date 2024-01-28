package com.cmps312.pizzapal.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(
    foreignKeys = [
        ForeignKey(
            entity = Order::class,
            parentColumns = ["id"],
            childColumns = ["orderId"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE,
        )
    ]
)
class Pizza (
    val name: String,
    val price: Double,
    val description: String,
    val imageName: String,
    val orderId : Int,
    val quantity : Int,
    @PrimaryKey(autoGenerate = true)
    val id: Int=0
)