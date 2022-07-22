package com.example.roomapp.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.roomapp.database.AstroidsDatabase
import java.lang.IllegalArgumentException

class AstroidsViewModelFactory (
    private val dataSource: AstroidsDatabase,
    private val application: Application):ViewModelProvider.Factory{
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainAstroidsViewModel::class.java)){
            return MainAstroidsViewModel(dataSource,application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }


}