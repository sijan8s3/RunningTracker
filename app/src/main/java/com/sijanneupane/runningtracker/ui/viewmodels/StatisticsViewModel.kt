package com.sijanneupane.runningtracker.ui.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.sijanneupane.runningtracker.repositories.MainRepository
import javax.inject.Inject

class StatisticsViewModel @ViewModelInject constructor(
    val mainRepository: MainRepository
): ViewModel() {

    val totalTimeRun= mainRepository.getTotalTimeInMillis()
    val totalDistance= mainRepository.getTotalDistance()
    val totalCalories= mainRepository.getTotalCaloriesBurned()
    val totalAvgSpeed= mainRepository.getTotalAvgSpeed()

    val runSortedByDate= mainRepository.getAllRunsSortedByDate()

}