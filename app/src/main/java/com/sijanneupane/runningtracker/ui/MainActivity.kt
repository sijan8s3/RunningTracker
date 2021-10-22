package com.sijanneupane.runningtracker.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.sijanneupane.runningtracker.R
import com.sijanneupane.runningtracker.database.RunDAO
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        val navHostFragment= supportFragmentManager.findFragmentById(R.id.runNavHostFrag) as NavHostFragment
        val navController= navHostFragment.navController
        bottomNavigationView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
                when(destination.id){
                    R.id.settingFragment, R.id.runFragment, R.id.statisticsFragment ->
                     bottomNavigationView.visibility= View.VISIBLE
                    else -> bottomNavigationView.visibility= View.GONE
                }

            }
    }
}
