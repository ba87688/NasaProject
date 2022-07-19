package com.example.roomapp.repository

import android.net.Network
import androidx.lifecycle.LiveData
import androidx.room.withTransaction
import com.example.astroidnasa.network.RetrofitInstance
import com.example.astroidnasa.retrofitmodels.AstroidApiModel
import com.example.roomapp.api.AstroidMade
import com.example.roomapp.api.parseAstroid
import com.example.roomapp.database.AstroidMadeDatabase
import com.example.roomapp.util.networkBoundResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import retrofit2.Response

class AstroidRepository(private val database: AstroidMadeDatabase) {

    val service = RetrofitInstance.api
    val astroids: LiveData<List<AstroidMade>> = database.assDatabaseDao.getAllNights()

    fun getRestaurants() = networkBoundResource(
        query = {
            database.assDatabaseDao.getRestaurants()
        },
        fetch = {
            delay(2000)
            val data = service.getAstroids()
            val pr = parseAstroid(data.body()!!)
            pr
        },
        saveFetchResult = { astroid->

            database.withTransaction {
                database.assDatabaseDao.deleteAllAstroids()
                database.assDatabaseDao.insertList(astroid)
            }
        }
    )


    suspend fun refreshAstroids() {
        withContext(Dispatchers.IO) {


        }
    }
    suspend fun getAstroid(): AstroidApiModel? {


        var re = service.getAstroids2()

        return re.body()

    }
    suspend fun getAstroid(a: String, b: String): AstroidApiModel? {


        var re = service.getAstroids2(a, b)

        return re.body()

    }

    fun getAllNights(): LiveData<List<AstroidMade>> {
        return database.assDatabaseDao.getAllNights()
    }

    suspend fun insertList(astroidList: MutableList<AstroidMade>) {
        database.assDatabaseDao.insertList(astroidList)
    }

    suspend fun insert(astroid: AstroidMade) {
        database.assDatabaseDao.insert(astroid)
    }

    suspend fun get(key: Int): AstroidMade {
        return database.assDatabaseDao.get(key)
    }


}