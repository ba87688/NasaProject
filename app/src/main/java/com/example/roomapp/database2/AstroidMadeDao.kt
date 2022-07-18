package com.example.roomapp.database2

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.roomapp.api.AstroidMade

@Dao
interface AstroidMadeDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(astroid: AstroidMade)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertList(astroidList: MutableList<AstroidMade>)


    @Query("SELECT * from astroid_table WHERE id=:key")
    fun get(key:Int) : AstroidMade


}