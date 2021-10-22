package com.sijanneupane.runningtracker.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sijanneupane.runningtracker.R
import com.sijanneupane.runningtracker.database.RunDAO
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var runDAO: RunDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
