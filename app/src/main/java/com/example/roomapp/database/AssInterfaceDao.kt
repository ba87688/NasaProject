package com.example.roomapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.roomapp.model.Ass

@Dao
interface AssInterfaceDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(ass: Ass)



    @Query("SELECT * from daily_ass WHERE nightId=:key")
    fun get(key:Long) : Ass






}