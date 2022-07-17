package com.example.roomapp.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.astroidnasa.network.RetrofitInstance
import com.example.astroidnasa.retrofitmodels.AstroidApiModel
import com.example.roomapp.database.AssInterfaceDao
import com.example.roomapp.database.AstroidRoomDatabase
import com.example.roomapp.repository.AstroidRepository
import kotlinx.coroutines.*

class AstroidMainViewModel(
    val database:AssInterfaceDao, application: Application
): AndroidViewModel(application) {
    private lateinit var repository: AstroidRepository
    private lateinit var s : AstroidApiModel

//    lateinit var astroidList: MutableLiveData<>

//    to do 1. initialize a job to manage our courotines.
    private var viewModelJob = Job()
    //viewmodel job allows us to cancel all corotines. where will corotines come bak to?
//    to do 2. cancel job in oncleared.
//    to do 3. need  a scope for corotines to run in. we pass in dispatched and job
    private val uiScope = viewModelScope


    init {

        repository = AstroidRepository()
        createList()

        initializeTonight()


    }
    fun initializeTonight(){
        viewModelScope.launch{
            val toe = getTonightFromDatabase()
            Log.i("Scooby", "initializeTonight: $toe ")
        }
    }

    private suspend fun getTonightFromDatabase(): String?{
        return withContext(Dispatchers.IO){
            var name = database.get(11).name
            name.toString()

        }

    }
    fun createList(){
        viewModelScope.launch {
            Log.i("WTF viewmodel", "createList: WTF ")

            val re = RetrofitInstance.api.getAstroids()
            s = re.body()!!
            Log.i("RETRO", "createList: $s")

//            s = repository.getAstroid()!!

        }



    }



    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }


}