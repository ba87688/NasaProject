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
import kotlinx.coroutines.launch

class AstroidMainViewModel(
    val database:AssInterfaceDao, application: Application
): AndroidViewModel(application) {
    private lateinit var repository: AstroidRepository
    private lateinit var s : AstroidApiModel

//    lateinit var astroidList: MutableLiveData<>

    init {

        repository = AstroidRepository()
        createList()



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
    }


}