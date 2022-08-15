package com.example.drinks.data.model

import com.example.drinks.vo.Resource

class DataSource {
    val generateDrinkList = Resource.Success(listOf(
        Drink("https://cdn.shopify.com/s/files/1/0255/9616/6180/files/drinks_1024x1024.png?v=1647427700","holaaa bebecita","azucar"),
        Drink("https://www.thecocktaildb.com/images/media/drink/5noda61589575158.jpg","hielo","miel"),
        Drink("https://www.thecocktaildb.com/images/media/drink/5noda61589575158.jpg","genu","sal"),
        Drink("https://www.thecocktaildb.com/images/media/drink/5noda61589575158.jpg","genu","limon"),
    ))

}