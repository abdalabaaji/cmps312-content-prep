package com.cmps312.pizzapal.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Order(
    val customerName: String,
    val address: String,
    val phone: String,
    val email: String,

    @PrimaryKey(autoGenerate = true)
    val id: Int=0
)