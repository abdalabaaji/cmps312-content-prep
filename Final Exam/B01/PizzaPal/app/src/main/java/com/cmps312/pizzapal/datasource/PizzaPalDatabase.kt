package com.cmps312.pizzapal.datasource

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.cmps312.pizzapal.entity.Order
import com.cmps312.pizzapal.entity.Pizza

@Database(entities = [Order::class, Pizza::class], version = 1)
abstract class PizzaPalDatabase : RoomDatabase() {

    abstract fun pizzaDao(): PizzaPalDao

    companion object {
        private var database: PizzaPalDatabase? = null

        fun getDatabase(context: Context): PizzaPalDatabase {
            if (database == null) {
                database = Room.databaseBuilder(
                    context.applicationContext,
                    PizzaPalDatabase::class.java,
                    "pizza_pal"
                ).fallbackToDestructiveMigration().build()
            }
            return database as PizzaPalDatabase
        }
    }
}