package com.cmps312.pizzapal.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.cmps312.pizzapal.entity.Order
import com.cmps312.pizzapal.entity.Pizza
import com.cmps312.pizzapal.repository.PizzaPalRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


class PizzaPalViewModel(appContext: Application) : AndroidViewModel(appContext) {

    private val pizzaRepository by lazy { PizzaPalRepo(appContext) }
    var customersFlow = pizzaRepository.observeCustomers().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    lateinit var pizzas: Flow<List<Pizza>>
    lateinit var selectedPizza: Pizza
    lateinit var selectedOrder: Order

    var isEditMode = false

    fun getPizzas(order: Order) {
        pizzas = pizzaRepository.observePizzas(order.id)
    }

    fun addPizza(pizza: Pizza) {
        viewModelScope.launch(Dispatchers.IO) {
            pizzaRepository.addPizza(pizza)
        }
    }

    fun deleteCustomer(order: Order) {
        viewModelScope.launch(Dispatchers.IO) {
            pizzaRepository.deleteOrder(order)
        }
    }

    fun updatePizza(pizza: Pizza) {
        viewModelScope.launch(Dispatchers.IO) {
            pizzaRepository.updatePizza(pizza)
        }
    }

}