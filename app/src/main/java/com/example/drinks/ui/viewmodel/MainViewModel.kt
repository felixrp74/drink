package com.example.drinks.ui.viewmodel

import androidx.lifecycle.*
import com.example.drinks.data.model.DrinkEntity
import com.example.drinks.data.source.Repo
import com.example.drinks.data.vo.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repo: Repo):ViewModel() {

    private val drinkName = MutableLiveData<String>()

    init {
        drinkName.value = "daiquiri"
    }

    fun setTrago(query: String) {
        drinkName.value = query
    }

    val fectchDrinkList = drinkName.distinctUntilChanged().switchMap {
        liveData(Dispatchers.IO) {
            emit(Resource.Loading())
            try {
                emit(repo.getDrinkList(it))
            }catch (e:Exception){
                emit(Resource.Failure(e))
            }
        }
    }

    fun insertFavoriteDrink(drinkEntity: DrinkEntity){
        viewModelScope.launch {
            repo.insertFavoriteDrink(drinkEntity)
        }
    }

    fun getFavoriteDrinkList() = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(repo.getFavoriteDrinkList())
        }catch (e:Exception){
            emit(Resource.Failure(e))
        }
    }

    fun deleteFavoriteDrink(drinkEntity: DrinkEntity){
        viewModelScope.launch {
            repo.deleteFavoriteDrink(drinkEntity)
        }
    }


}