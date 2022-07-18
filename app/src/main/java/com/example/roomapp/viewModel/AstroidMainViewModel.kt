package com.example.roomapp.viewModel

import android.app.Application
import android.content.ContentValues
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.astroidnasa.retrofitmodels.AstroidApiModel
import com.example.roomapp.api.AstroidMade
import com.example.roomapp.api.Constants
import com.example.roomapp.database.AstroidMadeDatabase
import com.example.roomapp.repository.AstroidRepository
import kotlinx.coroutines.*
import java.util.*
import kotlin.collections.ArrayList

class AstroidMainViewModel(
    val database: AstroidMadeDatabase, application: Application
) : AndroidViewModel(application) {


    private lateinit var repository: AstroidRepository
    private lateinit var s: AstroidApiModel

    lateinit var astroidList: MutableLiveData<List<AstroidMade>>


    init {

        repository = AstroidRepository(database)
        createList()



    }

//    private suspend fun getTonightFromDatabase(): String?{
//        return withContext(Dispatchers.IO){
//            var name = database.get(11).name
//            name.toString()
//        }
//    }

    fun getLiveData(): LiveData<List<AstroidMade>> {
        return repository.getAllNights()

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


    //api call
    suspend fun getAstroid( a:String,b: String): AstroidApiModel? {



        val re = repository.getAstroid(a,b)
        return re
    }
    fun getDates():ArrayList<String>{
        Log.i(ContentValues.TAG, "format dates: ")
        val formattedDateList = ArrayList<String>()

        val calendar = Calendar.getInstance()
        for (i in 0..1) {
            val currentTime = calendar.time
            val dateFormat = SimpleDateFormat(Constants.API_QUERY_DATE_FORMAT, Locale.getDefault())
            formattedDateList.add(dateFormat.format(currentTime))
            calendar.add(Calendar.DAY_OF_YEAR, 1)
        }

        Log.i("FROM VIEW", "getDates: ${formattedDateList[0]}")
        return formattedDateList

    }
    fun createList() {
        viewModelScope.launch {
            Log.i("WTF viewmodel", "createList: WTF ")
            withContext(Dispatchers.IO) {
                repository.getAllNights()


            }


        }


    }


    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }


}