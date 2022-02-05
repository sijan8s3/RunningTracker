package com.sijanneupane.runningtracker.di

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.room.Room
import com.google.android.gms.location.FusedLocationProviderClient
import com.sijanneupane.runningtracker.R
import com.sijanneupane.runningtracker.database.RunningDatabase
import com.sijanneupane.runningtracker.other.Constants
import com.sijanneupane.runningtracker.ui.MainActivity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ServiceComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ServiceScoped

//module that holds all the dependencies for tracking service
//only live as long as the tracking service does, not the whole app run

@Module
//not singleton(do not run whole app run)
// so not installing inside Singleton component
//so, choosing the servicecomp

@InstallIn(ServiceComponent::class)
object ServiceModule {

    @ServiceScoped
    @Provides
    fun provideFuseLocationProviderClient(
        @ApplicationContext app: Context
    )= FusedLocationProviderClient(app)


    @ServiceScoped
    @Provides
fun provideMainActivityPendingIntent(
        @ApplicationContext app: Context
)= PendingIntent.getActivity(
        app,
        0,
        Intent(app, MainActivity::class.java).also {
            it.action = Constants.ACTION_SHOW_TRACKING_FRAGMENT
        },
        PendingIntent.FLAG_UPDATE_CURRENT
    )

    @ServiceScoped
    @Provides
    fun provideBaseNotificationBuilder(
        @ApplicationContext app: Context,
        pendingIntent: PendingIntent
    )= NotificationCompat.Builder(
        app, Constants.NOTIFICATION_CHANNEL_ID
    ).setAutoCancel(false)
        .setOngoing(true)
        .setSmallIcon(R.drawable.ic_directions_run_black_24dp)
        .setContentTitle("Running Tracker")
        .setContentText("00:00:00")
        .setContentIntent(pendingIntent)

}