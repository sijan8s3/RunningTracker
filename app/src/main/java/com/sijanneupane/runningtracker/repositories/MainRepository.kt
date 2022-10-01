package com.sijanneupane.runningtracker.repositories

import com.sijanneupane.runningtracker.database.Run
import com.sijanneupane.runningtracker.database.RunDAO
import javax.inject.Inject

class MainRepository @Inject constructor(
    val runDAO: RunDAO
) {

    suspend fun insertRun(run: Run)= runDAO.insertRun(run)


    suspend fun deleteRun(run: Run)= runDAO.deleteRun(run)

    fun getAllRunsSortedByDate() = runDAO.getAllRunsSortedByDate()

    fun getAllRunsSortedByDistance() = runDAO.getAllRunsSortedByDistance()

    fun getAllRunsSortedByTimeInMillis() = runDAO.getAllRunsSortedByTimeInMillis()

    fun getAllRunsSortedByAvgSpeed() = runDAO.getAllRunsSortedByAverageSpeed()

    fun getAllRunsSortedByCaloriesBurned() = runDAO.getAllRunsSortedByCaloriesBurned()

    fun getTotalDistance()= runDAO.getTotalDistance()
    fun getTotalAvgSpeed()= runDAO.getTotalAverageSpeed()
    fun getTotalCaloriesBurned()= runDAO.getTotalCaloriesBurned()
    fun getTotalTimeInMillis()= runDAO.getTotalTimeInMills()





}