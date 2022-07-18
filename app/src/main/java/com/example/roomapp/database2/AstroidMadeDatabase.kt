package com.example.roomapp.database


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roomapp.api.AstroidMade
import com.example.roomapp.database2.AstroidMadeDao


@Database(entities = [AstroidMade::class], version = 1, exportSchema = false)
abstract class AstroidMadeDatabase : RoomDatabase(){

    abstract val assDatabaseDao: AstroidMadeDao

    companion object{

        @Volatile
        private var INSTANCE: AstroidMadeDatabase? = null

        fun getInstance(context: Context):AstroidMadeDatabase{
            synchronized(this){
                var instance = AstroidMadeDatabase.INSTANCE
                if(instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AstroidMadeDatabase::class.java,
                        "astroid_history"
                    ).fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance

            }
        }
    }

}