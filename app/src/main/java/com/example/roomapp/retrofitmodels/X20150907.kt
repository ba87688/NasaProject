package com.example.astroidnasa.retrofitmodels

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class X20150907(
    val absolute_magnitude_h: Double,   //yea
    val close_approach_data: List<CloseApproachData>, //tea
    val estimated_diameter: EstimatedDiameter, //yea
    val id: String,     //yea
    val is_potentially_hazardous_asteroid: Boolean //yea
)