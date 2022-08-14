package com.example.drinks.data.model

import com.example.drinks.vo.Resource

class DataSource {
    val generateDrinkList = listOf(
        Drink("https://www.thecocktaildb.com/images/media/drink/5noda61589575158.jpg","genu","azucar"),
        Drink("https://www.thecocktaildb.com/images/media/drink/5noda61589575158.jpg","hielo","miel"),
        Drink("https://www.thecocktaildb.com/images/media/drink/5noda61589575158.jpg","genu","sal"),
        Drink("https://www.thecocktaildb.com/images/media/drink/5noda61589575158.jpg","genu","limon"),
    )

    fun getGenerateList():Resource<List<Drink>>{
        return Resource.Success(generateDrinkList)
    }
}