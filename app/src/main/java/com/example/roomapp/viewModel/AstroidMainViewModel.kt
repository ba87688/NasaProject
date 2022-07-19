package com.example.roomapp.viewModel

import android.app.Application
import android.content.ContentValues
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.util.Log
import androidx.lifecycle.*
import com.example.astroidnasa.fragments.MainFragment
import com.example.astroidnasa.retrofitmodels.AstroidApiModel
import com.example.roomapp.adapter.AstroMadeAdapter
import com.example.roomapp.api.AstroidMade
import com.example.roomapp.api.Constants
import com.example.roomapp.api.parseAstroid
import com.example.roomapp.database.AstroidMadeDatabase
import com.example.roomapp.repository.AstroidRepository
import kotlinx.coroutines.*
import java.util.*
import kotlin.collections.ArrayList

class AstroidMainViewModel(
    val database: AstroidMadeDatabase, application: Application
) : AndroidViewModel(application) {


    private var repository: AstroidRepository = AstroidRepository(database)
    private lateinit var s: AstroidApiModel






    val restaurants = repository.getRestaurants().asLiveData()


    val videolist:LiveData<List<AstroidMade>>
        get() = _astroidList
    private val _astroidList = MutableLiveData<List<AstroidMade>>()


    init {


//        repository = AstroidRepository(database)
        createRestaurants()
        createList()

        Log.i("Started", "viewmodel started: ")


    }

    fun createRestaurants(){
        val restaurants = repository.getRestaurants().asLiveData()

    }


    fun getLiveData(): LiveData<List<AstroidMade>> {

        return videolist

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

    fun parseData(d:AstroidApiModel):List<AstroidMade>{
        return parseAstroid(d)
    }


    //api call
    suspend fun getAstroid( a:String,b: String): AstroidApiModel? {
        val re = repository.getAstroid(a,b)

        return re

    }

    fun createList() {
        viewModelScope.launch {
            Log.i(ContentValues.TAG, "onCreateView: thread frag started")

            Log.i("WTF viewmodel", "createList: WTF ")
            withContext(Dispatchers.IO) {
                val george = repository.getAstroid()
                Log.i("WTF viewmodel", "createList: WTF ${george} ")

                val data = parseData(george!!)


                withContext(Dispatchers.Main) {

                    _astroidList.value = data
                    Log.i("WTF viewmodel", "createList: WTF ${_astroidList.value} ")
                    Log.i("WTF viewmodel", "createList: WTF ${videolist.value} ")

                }



            }


        }


    }


    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }


}