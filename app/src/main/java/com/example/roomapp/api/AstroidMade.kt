package com.example.roomapp.api


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "astroid_table")
data class AstroidMade(
    @PrimaryKey()
    val id: Int,
    @ColumnInfo(name = "name_of_thing")
    val name: String,
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
) {
//    @PrimaryKey(autoGenerate = true)
//    var idd: Int=0
}