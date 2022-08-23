package com.example.drinks.domain

import com.example.drinks.data.model.DataSource
import com.example.drinks.data.model.Drink
import com.example.drinks.data.model.DrinkEntity
import com.example.drinks.vo.Resource

class RepoImpl(val dataSource: DataSource):Repo {
    override suspend fun getDrinkList(drinkName:String): Resource<List<Drink>> {
        return dataSource.getDrinkByName(drinkName)
    }

    override suspend fun getFavoriteDrinkList(): Resource<List<DrinkEntity>> {
        return dataSource.getFavoriteDrinks()
    }

    override suspend fun insertFavoriteDrink(drinkEntity: DrinkEntity) {
        dataSource.insertFavoriteDrinkIntoRoom(drinkEntity)
    }
}