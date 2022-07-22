package com.example.roomapp.images

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Entity(tableName = "image_of_the_day_table")
@Parcelize
data class ImageOfTheDay(
    @ColumnInfo(name = "copyright")
    val copyright: String,
    @ColumnInfo(name = "date")
    val date: String,
    @ColumnInfo(name = "explanation")
    val explanation: String,
    @ColumnInfo(name = "hdurl")
    val hdurl: String,
    @ColumnInfo(name = "media_type")
    val media_type: String,
    @ColumnInfo(name = "service_version")
    val service_version: String,
    @ColumnInfo(name = "title")
    val title: String,
    @PrimaryKey()
    val url: String
): Parcelable