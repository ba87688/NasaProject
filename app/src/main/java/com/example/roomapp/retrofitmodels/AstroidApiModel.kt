package com.example.astroidnasa.retrofitmodels

data class AstroidApiModel(
    val element_count: Int,
    val near_earth_objects: NearEarthObjects
)