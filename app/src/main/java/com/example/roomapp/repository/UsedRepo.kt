package com.example.roomapp.repository

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.lifecycleScope
import androidx.room.withTransaction
import com.example.astroidnasa.network.RetrofitInstance
import com.example.astroidnasa.retrofitmodels.AstroidApiModel
import com.example.roomapp.api.AstroidMade
import com.example.roomapp.api.Constants
import com.example.roomapp.api.parseAsteroidsJsonResult
import com.example.roomapp.api.parseAstroid
import com.example.roomapp.database.AstroidMadeDatabase
import com.example.roomapp.images.ImageOfTheDay
import com.example.roomapp.util.networkBoundResource
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class UsedRepo(private val database: AstroidMadeDatabase) {

    val service = RetrofitInstance.api1
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
    suspend fun getImage():Response<ImageOfTheDay>{
        return service.getPictureOfTheDay()
    }





    suspend fun getRealParsedResponse(){
            withContext(Dispatchers.IO){

                val formattedDateList = ArrayList<String>()

                val calendar = Calendar.getInstance()
                for (i in 0..Constants.DEFAULT_END_DATE_DAYS) {
                    val currentTime = calendar.time
                    val dateFormat = SimpleDateFormat(Constants.API_QUERY_DATE_FORMAT, Locale.getDefault())
                    formattedDateList.add(dateFormat.format(currentTime))
                    calendar.add(Calendar.DAY_OF_YEAR, 1)
                }

                Log.i(ContentValues.TAG, "onCreateView1: $formattedDateList")
                Log.i(ContentValues.TAG, "onCreateView1: ${formattedDateList.first()}")
                Log.i(ContentValues.TAG, "onCreateView1: ${formattedDateList.last()}")

                val red= RetrofitInstance.api1.getAstroids3(formattedDateList.first(),formattedDateList.last())
                val red1= RetrofitInstance.api1.getAstroids2()
                Log.i(ContentValues.TAG, "onCreateView1: fragment 2 ${red}")
                val you= Gson().toJson(red)
                val you1 = JSONObject(you)



                val pased = parseAsteroidsJsonResult(you1)

                Log.i(ContentValues.TAG, "onCreateView1: fragment 2 ${pased.toString()}")



            }


    }


}