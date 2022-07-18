package com.example.roomapp.database2

import androidx.lifecycle.LiveData
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

    @Query("SELECT * FROM astroid_table ORDER BY id DESC")
    fun getAllNights(): LiveData<List<AstroidMade>>


}