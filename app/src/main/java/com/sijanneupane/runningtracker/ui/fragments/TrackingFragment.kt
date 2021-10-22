package com.sijanneupane.runningtracker.ui.fragments

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.sijanneupane.runningtracker.R
import com.sijanneupane.runningtracker.ui.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TrackingFragment : Fragment(R.layout.fragment_tracking) {

    //get viewmodel from dagger
    private val viewModel: MainViewModel by viewModels()
}