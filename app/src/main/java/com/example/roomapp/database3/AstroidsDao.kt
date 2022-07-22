package com.example.roomapp.database3

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.roomapp.api.Astroid
import com.example.roomapp.api.AstroidMade
import com.example.roomapp.images.ImageOfTheDay
import kotlinx.coroutines.flow.Flow

@Dao
interface AstroidsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(astroid: Astroid)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertList(astroidList: MutableList<Astroid>)


    @Query("SELECT * from astroids_table WHERE id=:key")
    fun get(key:Int) : Astroid

    @Query("SELECT * FROM astroids_table ORDER BY id DESC")
    fun getAllNights(): LiveData<List<Astroid>>

    @Query("SELECT * FROM astroids_table ORDER BY id DESC")
    fun getRestaurants(): Flow<List<Astroid>>

    


    @Query("SELECT * FROM astroids_table ORDER BY id DESC")
    fun getAllListNights(): List<Astroid>

    @Query("DELETE FROM astroids_table")
    suspend fun deleteAllAstroids()


    //image class





    //image of the day data

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertImage(image: ImageOfTheDay)

    @Query("SELECT * from image_of_the_day_table WHERE url=:key")
    fun getImage(key:Int) : ImageOfTheDay

    @Query("SELECT * FROM image_of_the_day_table")
    fun getRestaurants2(): Flow<ImageOfTheDay>

    @Query("DELETE FROM image_of_the_day_table")
    suspend fun deleteImages()



}