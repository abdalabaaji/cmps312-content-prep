package com.cmps312.pizzapal.repository

import android.content.Context
import com.cmps312.pizzapal.datasource.PizzaPalDatabase
import com.cmps312.pizzapal.entity.Order
import com.cmps312.pizzapal.entity.Pizza
import kotlinx.coroutines.flow.Flow


class PizzaPalRepo(appContext: Context) {
//    add the Room annotations

    private val pizzaPalDao by lazy { PizzaPalDatabase.getDatabase(appContext).pizzaDao() }

    fun addOrder(order: Order) = pizzaPalDao.addOrder(order)

    fun deleteOrder(order: Order) = pizzaPalDao.deleteOrder(order)

    fun observeCustomers(): Flow<List<Order>> = pizzaPalDao.observeCustomers()

    fun addPizza(pizza: Pizza) = pizzaPalDao.addPizza(pizza)

    fun observePizzas(cid: Int): Flow<List<Pizza>> = pizzaPalDao.observePizzas(cid)

    fun updatePizza(pizza: Pizza) = pizzaPalDao.updatePizza(pizza)

    fun getPizza(id: Int) = pizzaPalDao.getPizza(id)
}