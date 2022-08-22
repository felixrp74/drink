package com.example.drinks.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Drink(
    @SerializedName("idDrink")
    val idDrink : String = "",
    @SerializedName("strDrinkThumb")
    val image: String = "",
    @SerializedName("strDrink")
    val name: String = "",
    @SerializedName("strInstructions")
    val description: String = ""

) : Parcelable


data class ListDrinks(
    @SerializedName("drinks")
    val drinkList: List<Drink>
)

@Entity(tableName = "tragoEntity")
data class DrinkEntity(
    @PrimaryKey
    val idDrink: String,
    @ColumnInfo( name = "strDrinkThumb")
    val image: String = "",
    @ColumnInfo( name = "strDrink")
    val name: String = "",
    @ColumnInfo( name = "strInstructions")
    val description: String = ""
)