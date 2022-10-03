package com.sijanneupane.runningtracker.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.sijanneupane.runningtracker.R
import com.sijanneupane.runningtracker.other.TrackingUtility
import com.sijanneupane.runningtracker.ui.viewmodels.MainViewModel
import com.sijanneupane.runningtracker.ui.viewmodels.StatisticsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_statistics.*
import kotlinx.android.synthetic.main.item_run.*
import kotlin.math.round

@AndroidEntryPoint
class StatisticsFragment : Fragment(R.layout.fragment_statistics) {

    private val viewModel: StatisticsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeToObservers()
    }

    private fun subscribeToObservers() {
        viewModel.totalTimeRun.observe(viewLifecycleOwner, Observer {
            it?.let {
                val totalTimeRun = TrackingUtility.getFormattedStopWatchTime(it)
                tvTotalTime.text = totalTimeRun

            }
        })

        viewModel.totalDistance.observe(viewLifecycleOwner, Observer {
            it?.let {
                val km = it / 1000f
                val totalDistance = round(km * 10f) / 10f
                val totalDistanceString = "$totalDistance km"
                tvTotalDistance.text = totalDistanceString
            }
        })

        viewModel.totalAvgSpeed.observe(viewLifecycleOwner, Observer {
            it?.let {
                val avgSpeed = round(it * 10f) / 10f
                val avgSpeedString = "$avgSpeed km/hr"
                tvAverageSpeed.text = avgSpeedString
            }
        })
        viewModel.totalCalories.observe(viewLifecycleOwner, Observer {
            it?.let {
                val caloriesString = "$it kcl"
                tvCalories.text = caloriesString
            }
        })

    }
}