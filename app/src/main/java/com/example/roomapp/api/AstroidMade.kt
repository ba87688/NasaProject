package com.example.roomapp.api


import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "astroid_table")
data class AstroidMade(
    @PrimaryKey()
    val id: Int,
    @ColumnInfo(name = "name_of_thing")
    val name: String,
    @ColumnInfo(name = "close_approach_date")
    val closeApproachDate: String,
    @ColumnInfo(name = "name_of_ass")
    val absoluteMagnitude: Double,
    @ColumnInfo(name = "estimate")
    val estimatedDiameter: Double,
    @ColumnInfo(name = "isHazardous")
    val isPotentiallyHazardous: Boolean,
    @ColumnInfo(name = "kiloPerSec")
    val kilometerPerSecond: Double,
    @ColumnInfo(name = "astonomical")
    val astronomical: Double
): Parcelable {
//    @PrimaryKey(autoGenerate = true)
//    var idd: Int=0
}