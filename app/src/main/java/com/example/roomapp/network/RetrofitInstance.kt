package com.example.astroidnasa.network

import com.example.astroidnasa.network.AstroidApi.Companion.BASE_URL
import com.example.astroidnasa.retrofitmodels.AstroidApiModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {


    val api:AstroidApi by lazy {
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AstroidApi::class.java)
    }



}