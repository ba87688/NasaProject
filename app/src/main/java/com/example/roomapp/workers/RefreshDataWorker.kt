package com.example.roomapp.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.roomapp.api.parseAstroid
import com.example.roomapp.database.AstroidMadeDatabase.Companion.getDatabase
import com.example.roomapp.repository.AstroidsRepository
import com.example.roomapp.repository.UsedRepo
import retrofit2.HttpException

class RefreshDataWorker(appContext: Context, params: WorkerParameters):
    CoroutineWorker(appContext, params)  {


    companion object{
        const val WORK_NAME = "RefreshDataWorker"
    }


    override suspend fun doWork(): Result {
        val database = getDatabase(applicationContext)
        val repository = UsedRepo(database)

        return try{
            var data = repository.getAstroid()
            var datatostore = parseAstroid(data!!)
            database.assDatabaseDao.insertList(datatostore)

            Result.success()
        }catch (exception:HttpException){
            Result.retry()
        }
    }


}