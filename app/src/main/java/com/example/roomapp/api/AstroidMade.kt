package com.example.roomapp.api

data class AstroidMade(val id: String,
                       val absoluteMagnitude: Double,
                       val estimatedDiameter: Double,
                       val isPotentiallyHazardous: Boolean,
                       val kilometerPerSecond: Double,
                       val astronomical: Double) {

}