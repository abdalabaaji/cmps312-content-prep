package com.cmps312.pizzapal.datasource

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.cmps312.pizzapal.entity.Order
import com.cmps312.pizzapal.entity.Pizza
import kotlinx.coroutines.flow.Flow

@Dao
interface PizzaPalDao {
    //    add the Room annotations
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addOrder(order: Order)

    @Delete
    fun deleteOrder(order: Order)

    @Query("SELECT * FROM `Order`")
    fun observeCustomers(): Flow<List<Order>>

    @Insert
    fun addPizza(pizza: Pizza)

    @Query("SELECT * FROM Pizza WHERE orderId=:orderId")
    fun observePizzas(orderId: Int): Flow<List<Pizza>>

    @Upsert
    fun updatePizza(pizza: Pizza)

    @Query("SELECT * FROM Pizza WHERE id=:id")
    fun getPizza(id: Int) : Pizza
}