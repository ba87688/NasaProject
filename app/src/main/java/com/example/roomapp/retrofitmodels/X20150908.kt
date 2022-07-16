package com.example.astroidnasa.retrofitmodels

data class X20150908(
    val absolute_magnitude_h: Double,
    val close_approach_data: List<CloseApproachDataX>,
    val estimated_diameter: EstimatedDiameterX,
    val id: String,
    val is_potentially_hazardous_asteroid: Boolean,
    val is_sentry_object: Boolean,
    val links: LinksXX,
    val name: String,
    val nasa_jpl_url: String,
    val neo_reference_id: String
)