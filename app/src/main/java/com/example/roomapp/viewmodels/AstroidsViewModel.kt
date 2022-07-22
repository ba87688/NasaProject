package com.example.roomapp.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.astroidnasa.retrofitmodels.AstroidApiModel
import com.example.roomapp.api.Astroid
import com.example.roomapp.api.AstroidMade
import com.example.roomapp.api.parseAstroid
import com.example.roomapp.database.AstroidMadeDatabase
import com.example.roomapp.database.AstroidsDatabase
import com.example.roomapp.repository.AstroidRepository
import com.example.roomapp.repository.AstroidsRepository
import kotlinx.coroutines.*

class AstroidsViewModel(
    val database: AstroidsDatabase, application: Application
) : AndroidViewModel(application) {


    private var repository: AstroidsRepository = AstroidsRepository(database)
    private lateinit var s: AstroidApiModel


    val restaurants = repository.getRestaurants().asLiveData()


    val url: LiveData<String>
        get() = _url
    private val _url = MutableLiveData<String>()

    val explination: LiveData<String>
        get() = _explination
    private val _explination = MutableLiveData<String>()

    val title: LiveData<String>
        get() = _title
    private val _title = MutableLiveData<String>()

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


    suspend fun insert(astroid: Astroid) {
        repository.insert(astroid)
    }

    suspend fun get(key: Int): Astroid {
        return repository.get(key)
    }

    suspend fun insertList(astroidList: MutableList<Astroid>) {
        repository.insertList(astroidList)
    }

//    fun parseData(d: AstroidApiModel): List<Astroid> {
//        return parseAstroid(d)
//    }


    //api call
//    suspend fun getAstroid(a: String, b: String): AstroidApiModel? {
//        val re = repository.getAstroid(a, b)
//
//        return re
//
//    }

    fun createList() {
    }

    fun getUrl(): String {
        var url: String = ""
        var explanation: String = ""
        var title: String  = ""

        viewModelScope.launch {

            val imageObject = repository.getImage()

            if (imageObject.isSuccessful) {
                explanation = imageObject.body()?.explanation!!
                title = imageObject.body()?.title!!
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
                _explination.value = explanation
                _title.value = title

            }

        }

        return url


    }


    fun getList(int: Int){


    }
    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }


}