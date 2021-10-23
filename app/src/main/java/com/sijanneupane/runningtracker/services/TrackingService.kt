package com.sijanneupane.runningtracker.services

import android.content.Intent
import androidx.lifecycle.LifecycleService
import com.sijanneupane.runningtracker.other.Constants.ACTION_PAUSE_SERVICE
import com.sijanneupane.runningtracker.other.Constants.ACTION_START_OR_RESUME_SERVICE
import com.sijanneupane.runningtracker.other.Constants.ACTION_STOP_SERVICE
import timber.log.Timber

/*
inheriting from LifecycleService because we need to observe from live data object
inside of this service class,
so, observe function needs life cycle owner .
specifying LifecycleService to pass the instance of this LifecycleService
as a LifecycleOwner to that observe function
 */
class TrackingService: LifecycleService() {

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        intent?.let {
            when(it.action){
                ACTION_START_OR_RESUME_SERVICE ->{
                    Timber.d("Started or resumed service")
                }
                ACTION_PAUSE_SERVICE ->{
                    Timber.d("Paused service")
                }
                ACTION_STOP_SERVICE ->{
                    Timber.d("Stopped service")
                }
            }
        }

        return super.onStartCommand(intent, flags, startId)
    }
}