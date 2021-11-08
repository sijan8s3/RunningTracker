package com.sijanneupane.runningtracker.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.MarkerOptions
import com.sijanneupane.runningtracker.R
import com.sijanneupane.runningtracker.other.Constants.ACTION_START_OR_RESUME_SERVICE
import com.sijanneupane.runningtracker.services.Polyline
import com.sijanneupane.runningtracker.services.TrackingService
import com.sijanneupane.runningtracker.ui.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_tracking.*
import com.google.android.gms.maps.CameraUpdateFactory

import com.google.android.gms.maps.model.BitmapDescriptorFactory

import com.google.android.gms.maps.model.LatLng
import com.sijanneupane.runningtracker.other.Constants.MAP_ZOOM


@AndroidEntryPoint
class TrackingFragment : Fragment(R.layout.fragment_tracking) {

    //get viewmodel from dagger
    private val viewModel: MainViewModel by viewModels()

    private var pathPoints= mutableListOf<Polyline>()
    private  var map: GoogleMap? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mapView.onCreate(savedInstanceState)

        btnToggleRun.setOnClickListener{
        sendCommandToService(ACTION_START_OR_RESUME_SERVICE)
        }
        //27.6649405, 84.4226348
        val latLng = "27.6649405,84.4226348".split(",".toRegex()).toTypedArray()
        val latitude = latLng[0].toDouble()
        val longitude = latLng[1].toDouble()
        val location = LatLng(latitude, longitude)

        mapView.getMapAsync{
            map= it
            addMarker(location)
        }
    }

    private fun addMarker(latLng: LatLng){
        val markerOptions = MarkerOptions()

        markerOptions.apply {
            position(latLng)
            title("User Location")
            icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))

        }
        map?.apply {
            addMarker(markerOptions)
            animateCamera(
                CameraUpdateFactory.newLatLngZoom(
                    latLng, MAP_ZOOM
                )
            )
        }
    }



    private fun sendCommandToService(action: String) =
        Intent(requireContext(), TrackingService::class.java).also {
            it.action = action
            requireContext().startService(it)
        }

    override fun onResume() {
        super.onResume()
        mapView?.onResume()
    }

    override fun onStart() {
        super.onStart()
        mapView?.onStart()
    }

    override fun onStop() {
        super.onStop()
        mapView?.onStop()
    }

    override fun onPause() {
        super.onPause()
        mapView?.onPause()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView?.onLowMemory()
    }

//    override fun onDestroy() {
//        super.onDestroy()
//        mapView?.onDestroy()
//    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView?.onSaveInstanceState(outState)
    }
}