package com.example.roomapp.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.astroidnasa.retrofitmodels.AstroidApiModel
import com.example.roomapp.database.AstroidsDatabase
import com.example.roomapp.repository.AstroidsRepository
import kotlinx.coroutines.*

class MainAstroidsViewModel(
    val database: AstroidsDatabase, application: Application
) : AndroidViewModel(application) {


    private var repository: AstroidsRepository = AstroidsRepository(database)
    private lateinit var s: AstroidApiModel


    val restaurants = repository.getRestaurants().asLiveData()
    val dailyImage = repository.getRestaurants2().asLiveData()


    val url: LiveData<String>
        get() = _url
    private val _url = MutableLiveData<String>()


    init {


//        repository = AstroidRepository(database)
        createRestaurants()
//        createList()

        Log.i("Started", "viewmodel started: ")


    }

    fun createRestaurants() {
//        val restaurants = repository.getRestaurants().asLiveData()

    }


    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }


}