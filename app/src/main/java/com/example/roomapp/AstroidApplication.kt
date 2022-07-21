package com.example.roomapp

import android.app.Application
import androidx.work.*
import com.example.roomapp.workers.RefreshDataWorker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class AstroidApplication:Application() {

    private val applicationScope = CoroutineScope(Dispatchers.Default)

    override fun onCreate() {
        super.onCreate()
        delayedInit()
    }
    private fun delayedInit(){
        applicationScope.launch {
            setupRecurringWork()
        }
    }
    private fun setupRecurringWork(){
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.UNMETERED)
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresBatteryNotLow(true)
            .setRequiresCharging(true)
            .build()

        val repeatingRequest = PeriodicWorkRequestBuilder<RefreshDataWorker>(
            1,TimeUnit.DAYS)
            .setConstraints(constraints)
            .build()
        WorkManager.getInstance().enqueueUniquePeriodicWork(
            RefreshDataWorker.WORK_NAME,
            ExistingPeriodicWorkPolicy.KEEP,
            repeatingRequest)

    }
}