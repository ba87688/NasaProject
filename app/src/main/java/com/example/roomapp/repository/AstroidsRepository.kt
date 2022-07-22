package com.example.roomapp.repository

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.room.withTransaction
import com.example.astroidnasa.network.RetrofitInstance
import com.example.roomapp.api.*
import com.example.roomapp.database.AstroidMadeDatabase
import com.example.roomapp.database.AstroidsDatabase
import com.example.roomapp.images.ImageOfTheDay
import com.example.roomapp.util.networkBoundResource
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import org.json.JSONObject
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class AstroidsRepository(private val database: AstroidsDatabase) {

    val service = RetrofitInstance.api1
    val astroids: LiveData<List<Astroid>> = database.assDatabaseDao.getAllNights()
//
    fun getRestaurants() = networkBoundResource(
        query = {

            database.assDatabaseDao.getRestaurants()
        },
        fetch = {
            var pr : ArrayList<Astroid> = arrayListOf()
            withContext(Dispatchers.IO) {
                val data = service.getAstroids3()
                val you = Gson().toJson(data)
                val you1 = JSONObject(you)
                val pr1 = parseAsteroidsJsonResult(you1)
                withContext(Dispatchers.IO){
                    pr= pr1
                }
            }
            pr
        },
        saveFetchResult = { astroid->

            database.withTransaction {
                database.assDatabaseDao.deleteAllAstroids()
                database.assDatabaseDao.insertList(astroid)
            }
        }
    )
//

    fun getRestaurants2() = networkBoundResource(
        query = {



            database.assDatabaseDao.getRestaurants2()
        },
        fetch = {
            var image: ImageOfTheDay? = null
            withContext(Dispatchers.IO) {

                val imageObject = getImage().body()

                withContext(Dispatchers.IO){
                    image= imageObject
                }
            }
            image

        },
        saveFetchResult = { imageObject->

            database.withTransaction {
                database.assDatabaseDao.deleteImages()
                database.assDatabaseDao.insertImage(imageObject!!)
            }
        }
    )





    suspend fun refreshAstroids() {
        withContext(Dispatchers.IO) {


        }
    }


    fun getAllNights(): LiveData<List<Astroid>> {
        return database.assDatabaseDao.getAllNights()
    }

    suspend fun insertList(astroidList: MutableList<Astroid>) {
        database.assDatabaseDao.insertList(astroidList)
    }

    suspend fun insert(astroid: Astroid) {
        database.assDatabaseDao.insert(astroid)
    }

    suspend fun get(key: Int): Astroid {
        return database.assDatabaseDao.get(key)
    }
    suspend fun getImage(): Response<ImageOfTheDay> {
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