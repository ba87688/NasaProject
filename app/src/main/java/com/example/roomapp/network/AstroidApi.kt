package com.example.astroidnasa.network

import com.example.astroidnasa.models.Asteroid1
import com.example.astroidnasa.retrofitmodels.AstroidApiModel
import com.example.astroidnasa.retrofitmodels.NearEarthObjects
import com.example.astroidnasa.retrofitmodels.X20150907
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query


interface AstroidApi {

    companion object {
        const val BASE_URL = "https://api.nasa.gov"
        const val API_KEY = "HooxUNx7aEfz19YzqBEoLyX3peGuhfnNTHQ6flfM"
        const val API_KEY2 = "DEMO_KEY"

    }

//    @GET("businesses/search")
//    suspend fun searchRestuarants(
//        @Header("Authorization") autheader: String = "Bearer $API_KEY",
//        @Query("term") searchTerm: String = "Avocado",
//        @Query("location") location: String = "New York"
////        @Query("offset") offset:Int? = null
//    ): Response<YelpResponse>




    @GET("/neo/rest/v1/feed?start_date=2015-09-07&end_date=2015-09-08&api_key=DEMO_KEY")
    suspend fun getAstroids(): Response<AstroidApiModel>

//    @GET("/neo/rest/v1/feed?start_date=2015-09-07&end_date=2015-09-08&api_key=DEMO_KEY")
    @GET("/neo/rest/v1/feed")
    suspend fun getAstroids2(
        @Query("start_date") searchTerm: String = "2015-09-07",
        @Query("end_date") searchTerm2: String = "2015-09-08",
        @Query("api_key") autheader: String = "$API_KEY"


        ): Response<AstroidApiModel>


//
//
//    @GET("/neo/rest/v1/feed?start_date=2015-09-07&end_date=2015-09-08&api_key=DEMO_KEY")
//    suspend fun getAstroids(): Response<AstroidApiModel>
//
//    @GET("/neo/rest/v1/feed")
//    suspend fun getAstroids2(
//        @Query("start_date") searchTerm: String = "2015-09-07",
//        @Query("end_date") searchTerm2: String = "2015-09-08",
//        @Query("api_key") autheader: String = "$API_KEY"
//
//
//    ): Response<AstroidApiModel>
//
//

}