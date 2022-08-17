package com.example.drinks.ui.viewmodel

import androidx.lifecycle.*
import com.example.drinks.domain.Repo
import com.example.drinks.vo.Resource
import kotlinx.coroutines.Dispatchers

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




}