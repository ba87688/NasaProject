package com.example.astroidnasa.retrofitmodels

import com.google.gson.annotations.SerializedName

data class NearEarthObjects(

    @SerializedName("2022-07-18")
    val `2015-09-07`: List<X20150907>,
    @SerializedName("2022-07-19")
    val `2015-09-08`: List<X20150908>
)