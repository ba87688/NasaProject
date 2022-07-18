package com.example.roomapp.repository

import androidx.lifecycle.LiveData
import com.example.astroidnasa.network.RetrofitInstance
import com.example.astroidnasa.retrofitmodels.AstroidApiModel
import com.example.roomapp.api.AstroidMade
import com.example.roomapp.database.AstroidMadeDatabase

class AstroidRepository (private val database: AstroidMadeDatabase){

    val service = RetrofitInstance.api


    suspend fun getAstroid( a:String,b: String): AstroidApiModel? {



        val re = service.getAstroids2(a,b)
        return re.body()
    }

    fun getAllNights(): LiveData<List<AstroidMade>>{
        return database.assDatabaseDao.getAllNights()
    }



}