package com.sijanneupane.runningtracker.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface RunDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRun(run: Run)


    @Delete
    suspend fun deleteRun(run: Run)


    @Query("SELECT * FROM runningTable ORDER BY timeStamp DESC")
    fun getAllRunsSortedByDate(): LiveData<List<Run>>



    @Query("SELECT * FROM runningTable ORDER BY timeInMillis DESC")
    fun getAllRunsSortedByTimeInMillis(): LiveData<List<Run>>



    @Query("SELECT * FROM runningTable ORDER BY caloriesBurned DESC")
    fun getAllRunsSortedByCaloriesBurned(): LiveData<List<Run>>



    @Query("SELECT * FROM runningTable ORDER BY averageSpeedInKMH DESC")
    fun getAllRunsSortedByAverageSpeed(): LiveData<List<Run>>



    @Query("SELECT * FROM runningTable ORDER BY distanceInMeters DESC")
    fun getAllRunsSortedByDistance(): LiveData<List<Run>>



    @Query("SELECT SUM(timeInMillis) FROM runningTable")
    fun getTotalTimeInMills(): LiveData<Long>


    @Query("SELECT SUM(caloriesBurned) FROM runningTable")
    fun getTotalCaloriesBurned(): LiveData<Int>


    @Query("SELECT SUM(distanceInMeters) FROM runningTable")
    fun getTotalDistance(): LiveData<Int>


    @Query("SELECT AVG(averageSpeedInKMH) FROM runningTable")
    fun getTotalAverageSpeed(): LiveData<Float>
}







