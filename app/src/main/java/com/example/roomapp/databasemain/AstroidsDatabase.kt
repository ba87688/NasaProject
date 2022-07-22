package com.example.roomapp.database


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roomapp.api.Astroid
import com.example.roomapp.databasemain.AstroidsDao
import com.example.roomapp.images.ImageOfTheDay


@Database(entities = [Astroid::class, ImageOfTheDay::class], version = 1, exportSchema = false)
abstract class AstroidsDatabase : RoomDatabase(){

    abstract val assDatabaseDao: AstroidsDao

    companion object{

        @Volatile
        private var INSTANCE: AstroidsDatabase? = null

        fun getInstance(context: Context):AstroidsDatabase{
            synchronized(this){
                var instance = AstroidsDatabase.INSTANCE
                if(instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AstroidsDatabase::class.java,
                        "astroids_data"
                    ).fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance

            }
        }

        fun getDatabase(context: Context):AstroidsDatabase{
            return getInstance(context)
        }
    }

}