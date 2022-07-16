package com.example.roomapp.repository

import com.example.astroidnasa.network.RetrofitInstance
import com.example.astroidnasa.retrofitmodels.AstroidApiModel

class AstroidRepository {

    val service = RetrofitInstance.api


    suspend fun getAstroid(): AstroidApiModel? {

        val re = service.getAstroids2()
        return re.body()
    }
}