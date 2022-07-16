package com.example.roomapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "daily_ass")
data class Ass (
    @PrimaryKey(autoGenerate = true)
    var nightId: Long =0L,

    val startTime:Long = System.currentTimeMillis(),
    @ColumnInfo(name = "name_of_ass")
    val name: String
)
