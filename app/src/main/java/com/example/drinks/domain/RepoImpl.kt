package com.example.drinks.domain

import com.example.drinks.data.model.DataSource
import com.example.drinks.data.model.Drink
import com.example.drinks.vo.Resource

class RepoImpl(val dataSource: DataSource):Repo {
    override fun getDrinkList(): Resource<List<Drink>> {
        return dataSource.getGenerateList()
    }
}