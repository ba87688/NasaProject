package com.example.roomapp.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.astroidnasa.network.RetrofitInstance
import com.example.astroidnasa.retrofitmodels.AstroidApiModel
import com.example.roomapp.api.parseAsteroidsJsonResult
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



    init {

        repository = AstroidRepository()
        createList()

//        initializeTonight()


    }
//    fun initializeTonight(){
//        viewModelScope.launch{
//            val toe = getTonightFromDatabase()
//            Log.i("Scooby", "initializeTonight: $toe ")
//        }
//    }
//
//    private suspend fun getTonightFromDatabase(): String?{
//        return withContext(Dispatchers.IO){
//            var name = database.get(11).name
//            name.toString()
//
//        }
//
//    }
    fun createList(){
        viewModelScope.launch {
            Log.i("WTF viewmodel", "createList: WTF ")

//            val re = RetrofitInstance.api.getAstroids()
//            s = re.body()!!
//            Log.i("RETRO", "createList: $s")

//

//            s = repository.getAstroid()!!

        }



    }



    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }


}