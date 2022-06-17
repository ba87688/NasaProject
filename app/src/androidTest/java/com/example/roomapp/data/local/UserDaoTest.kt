package com.example.roomapp.data.local

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.roomapp.data.User
import com.example.roomapp.data.UserDao
import com.example.roomapp.data.UserDatabase
import com.example.roomapp.getOrAwaitValue
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule


@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class UserDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: UserDatabase
    private lateinit var dao:UserDao


    @Before
    fun setUp(){
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            UserDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.userDao()


    }

    @After
    fun teardown(){
        database.close()
    }

    fun insertUser() = runBlockingTest {
        val user = User(0, "Evan", "Maroge",33)
        dao.addUser(user)

        val users = dao.readAllData().getOrAwaitValue()
        assertThat(users).contains(user)

    }






































}