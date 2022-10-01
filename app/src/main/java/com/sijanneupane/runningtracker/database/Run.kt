package com.sijanneupane.runningtracker.database

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "runningTable")
data class Run (
    var img: Bitmap? = null,
    var timeStamp: Long = 0L,
    var averageSpeedInKMH: Float= 0f,
    var distanceInMeters: Int= 0,
    var timeInMillis: Long= 0L,
    var caloriesBurned: Int= 0
) {

    @PrimaryKey(autoGenerate = true)
    var id: Int?= null

}