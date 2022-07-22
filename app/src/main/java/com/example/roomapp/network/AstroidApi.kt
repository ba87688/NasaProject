package com.example.astroidnasa.network

import com.example.astroidnasa.retrofitmodels.AstroidApiModel
import com.example.roomapp.api.Constants.API_KEY
import com.example.roomapp.images.ImageOfTheDay
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface AstroidApi {


    @GET("/neo/rest/v1/feed?start_date=2015-09-07&end_date=2015-09-08&api_key=DEMO_KEY")
    suspend fun getAstroids(): Response<AstroidApiModel>

//    @GET("/neo/rest/v1/feed?start_date=2015-09-07&end_date=2015-09-08&api_key=DEMO_KEY")
    @GET("/neo/rest/v1/feed")
    suspend fun getAstroids2(
        @Query("start_date") searchTerm: String = "2015-09-07",
        @Query("end_date") searchTerm2: String = "2015-09-08",
        @Query("api_key") autheader: String = "$API_KEY"


        ): Response<AstroidApiModel>



    @GET("/neo/rest/v1/feed")
    suspend fun getAstroids3(
        @Query("start_date") searchTerm: String = "2022-07-22",
        @Query("end_date") searchTerm2: String = "2022-07-29",
        @Query("api_key") autheader: String = "$API_KEY"


    ): Object



    @GET("/planetary/apod")

    suspend fun getPictureOfTheDay(
        @Query("api_key") autheader: String = "$API_KEY"
    ):Response<ImageOfTheDay>

}