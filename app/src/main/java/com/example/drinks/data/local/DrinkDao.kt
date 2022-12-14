package com.example.drinks.data.local

import androidx.room.*
import com.example.drinks.data.model.Drink
import com.example.drinks.data.model.DrinkEntity

@Dao
interface DrinkDao {

    @Query("SELECT * FROM tragoEntity")
    suspend fun getAllFavoriteDrinksFromRoom():List<DrinkEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFavoriteDrinkIntoRoom(drinkEntity: DrinkEntity)

    @Delete
    fun deleteFavoriteDrink(drinkEntity: DrinkEntity)

}