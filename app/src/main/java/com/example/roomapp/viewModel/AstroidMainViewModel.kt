package com.example.roomapp.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.astroidnasa.retrofitmodels.AstroidApiModel
import com.example.roomapp.api.AstroidMade
import com.example.roomapp.api.parseAstroid
import com.example.roomapp.database.AstroidMadeDatabase
import com.example.roomapp.repository.AstroidRepository
import kotlinx.coroutines.*

class AstroidMainViewModel(
    val database: AstroidMadeDatabase, application: Application
) : AndroidViewModel(application) {


    private var repository: AstroidRepository = AstroidRepository(database)
    private lateinit var s: AstroidApiModel


    val restaurants = repository.getRestaurants().asLiveData()


    val url: LiveData<String>
        get() = _url

    private val _url = MutableLiveData<String>()

//    val videolist:LiveData<List<AstroidMade>>
//        get() = _astroidList
//    private val _astroidList = MutableLiveData<List<AstroidMade>>()
//

    init {


//        repository = AstroidRepository(database)
        createRestaurants()
        createList()

        Log.i("Started", "viewmodel started: ")


    }

    fun createRestaurants() {
        val restaurants = repository.getRestaurants().asLiveData()

    }


    suspend fun insert(astroid: AstroidMade) {
        repository.insert(astroid)
    }

    suspend fun get(key: Int): AstroidMade {
        return repository.get(key)
    }

    suspend fun insertList(astroidList: MutableList<AstroidMade>) {
        repository.insertList(astroidList)
    }

    fun parseData(d: AstroidApiModel): List<AstroidMade> {
        return parseAstroid(d)
    }


    //api call
    suspend fun getAstroid(a: String, b: String): AstroidApiModel? {
        val re = repository.getAstroid(a, b)

        return re

    }

    fun createList() {
    }

    fun getUrl(): String {
        var url: String = ""

        viewModelScope.launch {

            val imageObject = repository.getImage()
            if (imageObject.isSuccessful) {
                if (imageObject.body()?.media_type == "image") {
                    url = imageObject.body()?.url!!

                } else {
                    url = "https://apod.nasa.gov/apod/image/2001/STSCI-H-p2006a-h-1024x614.jpg"
                }


            } else {
                Log.i("There was an error", "in the code")

            }
            Log.i("IS BODY full", "createList: ${imageObject.body()?.date} ")
            Log.i("IS BODY full", "createList: $url ")
            withContext(Dispatchers.Main) {
                _url.value = url

            }

        }

        return url


    }


    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }


}