package com.example.drinks.domain

import com.example.drinks.data.model.DataSourceImpl
import com.example.drinks.data.model.Drink
import com.example.drinks.data.model.DrinkEntity
import com.example.drinks.vo.Resource

class RepoImpl(val dataSourceImpl: DataSourceImpl):Repo {
    override suspend fun getDrinkList(drinkName:String): Resource<List<Drink>> {
        return dataSourceImpl.getDrinkByName(drinkName)
    }

    override suspend fun getFavoriteDrinkList(): Resource<List<DrinkEntity>> {
        return dataSourceImpl.getFavoriteDrinks()
    }

    override suspend fun insertFavoriteDrink(drinkEntity: DrinkEntity) {
        dataSourceImpl.insertFavoriteDrinkIntoRoom(drinkEntity)
    }

    override suspend fun deleteFavoriteDrink(drinkEntity: DrinkEntity) {
        dataSourceImpl.deleteFavoriteDrinkFromRoom(drinkEntity)
    }
}