package com.example.roomapp.api


import android.content.ContentValues.TAG
import android.os.Parcelable
import android.util.Log
import com.example.astroidnasa.retrofitmodels.AstroidApiModel
import com.example.roomapp.model.Astroid
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

//data class Asteroid(val id: Long, val codename: String, val closeApproachDate: String,
//                    val absoluteMagnitude: Double, val estimatedDiameter: Double,
//                    val relativeVelocity: Double, val distanceFromEarth: Double,
//                    val isPotentiallyHazardous: Boolean) : Parcelable

fun parseAstroid(a: AstroidApiModel): MutableList<AstroidMade> {

    var listA = mutableListOf<AstroidMade>()


    val near = a.near_earth_objects

    var num = 0
    for (i in a.near_earth_objects.`2015-09-07`) {
        val id = i.id.toInt()
        val name = i.name.toString()
        val closeApproachDate = i.close_approach_data.get(0).close_approach_date.toString()
        val absoluteMagnitude = i.absolute_magnitude_h
        val estimatedDiameter: Double = i.estimated_diameter.feet.estimated_diameter_max
        val isPotentiallyHazardous: Boolean = i.is_potentially_hazardous_asteroid
        val kilometerPerSecond: Double =
            i.close_approach_data.get(0).relative_velocity.kilometers_per_second.toDouble()
        val astronomical: Double =
            i.close_approach_data.get(0).miss_distance.astronomical.toDouble()


        val astr = AstroidMade(
            id,
            name,
            closeApproachDate,
            absoluteMagnitude,
            estimatedDiameter,
            isPotentiallyHazardous,
            kilometerPerSecond,
            astronomical
        )

        listA.add(astr)
    }





    return listA

}


fun parseAsteroidsJsonResult(jsonResult: JSONObject): ArrayList<Asteroid> {
    val nearEarthObjectsJson = jsonResult.getJSONObject("near_earth_objects")

    val asteroidList = ArrayList<Asteroid>()

    val nextSevenDaysFormattedDates = getNextSevenDaysFormattedDates()
    for (formattedDate in nextSevenDaysFormattedDates) {
        if (nearEarthObjectsJson.has(formattedDate)) {
            val dateAsteroidJsonArray = nearEarthObjectsJson.getJSONArray(formattedDate)

            for (i in 0 until dateAsteroidJsonArray.length()) {
                val asteroidJson = dateAsteroidJsonArray.getJSONObject(i)
                val id = asteroidJson.getLong("id")
                val codename = asteroidJson.getString("name")
                val absoluteMagnitude = asteroidJson.getDouble("absolute_magnitude_h")
                val estimatedDiameter = asteroidJson.getJSONObject("estimated_diameter")
                    .getJSONObject("kilometers").getDouble("estimated_diameter_max")

                val closeApproachData = asteroidJson
                    .getJSONArray("close_approach_data").getJSONObject(0)
                val relativeVelocity = closeApproachData.getJSONObject("relative_velocity")
                    .getDouble("kilometers_per_second")
                val distanceFromEarth = closeApproachData.getJSONObject("miss_distance")
                    .getDouble("astronomical")
                val isPotentiallyHazardous = asteroidJson
                    .getBoolean("is_potentially_hazardous_asteroid")

                val asteroid = Asteroid(
                    id, codename, formattedDate, absoluteMagnitude,
                    estimatedDiameter, relativeVelocity, distanceFromEarth, isPotentiallyHazardous
                )
                asteroidList.add(asteroid)
            }
        }
    }

    return asteroidList
}

private fun getNextSevenDaysFormattedDates(): ArrayList<String> {
    val formattedDateList = ArrayList<String>()

    val calendar = Calendar.getInstance()
    for (i in 0..Constants.DEFAULT_END_DATE_DAYS) {
        val currentTime = calendar.time
        val dateFormat = SimpleDateFormat(Constants.API_QUERY_DATE_FORMAT, Locale.getDefault())
        formattedDateList.add(dateFormat.format(currentTime))
        calendar.add(Calendar.DAY_OF_YEAR, 1)
    }

    return formattedDateList
}