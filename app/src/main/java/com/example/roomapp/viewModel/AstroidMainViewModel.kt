package com.example.roomapp.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.astroidnasa.network.RetrofitInstance
import com.example.astroidnasa.retrofitmodels.AstroidApiModel
import com.example.roomapp.repository.AstroidRepository
import kotlinx.coroutines.launch

class AstroidMainViewModel: ViewModel() {
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