package com.example.roomapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roomapp.api.AstroidMade
import com.example.roomapp.database2.AstroidMadeDao
import com.example.roomapp.model.Ass


@Database(entities = [Ass::class], version = 1, exportSchema = false)
abstract class AstroidRoomDatabase : RoomDatabase(){

    abstract val assDatabaseDao: AssInterfaceDao

    companion object{

        @Volatile
        private var INSTANCE: AstroidRoomDatabase? = null

        fun getInstance(context: Context):AstroidRoomDatabase{
            synchronized(this){
                var instance = INSTANCE
                if(instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AstroidRoomDatabase::class.java,
                        "ass_history"
                    ).fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance

            }
        }
    }

}

//
//
//@Database(entities = [AstroidMade::class], version = 1, exportSchema = false)
//abstract class AstroidRoomDatabase : RoomDatabase(){
//
//    abstract val assDatabaseDao: AssInterfaceDao
//
//    companion object{
//
//        @Volatile
//        private var INSTANCE: AstroidRoomDatabase? = null
//
//        fun getInstance(context: Context):AstroidRoomDatabase{
//            synchronized(this){
//                var instance = INSTANCE
//                if(instance == null){
//                    instance = Room.databaseBuilder(
//                        context.applicationContext,
//                        AstroidRoomDatabase::class.java,
//                        "ass_history"
//                    ).fallbackToDestructiveMigration().build()
//                    INSTANCE = instance
//                }
//                return instance
//
//            }
//        }
//    }
//
//}