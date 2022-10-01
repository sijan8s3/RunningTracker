package com.sijanneupane.runningtracker.ui.fragments

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.sijanneupane.runningtracker.R
import com.sijanneupane.runningtracker.ui.viewmodels.MainViewModel
import com.sijanneupane.runningtracker.ui.viewmodels.StatisticsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StatisticsFragment : Fragment(R.layout.fragment_statistics) {

    //get viewmodel from dagger
    private val viewModel: StatisticsViewModel by viewModels()
}