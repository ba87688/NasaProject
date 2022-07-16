package com.example.roomapp.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.roomapp.database.AssInterfaceDao
import java.lang.IllegalArgumentException

class AstroidMainViewModelFactory (
    private val dataSource: AssInterfaceDao,
    private val application: Application):ViewModelProvider.Factory{
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(AstroidMainViewModel::class.java)){
            return AstroidMainViewModel(dataSource,application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }


}