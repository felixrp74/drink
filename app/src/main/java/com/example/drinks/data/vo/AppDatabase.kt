package com.example.drinks.data.vo

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.drinks.data.local.DrinkDao
import com.example.drinks.data.model.DrinkEntity

@Database(entities = [DrinkEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun drinkDao(): DrinkDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            INSTANCE = INSTANCE ?: Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java, "database-drinks"
            ).build()

            return INSTANCE!!

        }
    }
}
