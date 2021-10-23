package com.sijanneupane.runningtracker.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.PolylineOptions
import com.sijanneupane.runningtracker.R
import com.sijanneupane.runningtracker.other.Constants.ACTION_PAUSE_SERVICE
import com.sijanneupane.runningtracker.other.Constants.ACTION_START_OR_RESUME_SERVICE
import com.sijanneupane.runningtracker.other.Constants.MAP_ZOOM
import com.sijanneupane.runningtracker.other.Constants.POLYLINE_COLOR
import com.sijanneupane.runningtracker.other.Constants.POLYLINE_WIDTH
import com.sijanneupane.runningtracker.services.Polyline
import com.sijanneupane.runningtracker.services.TrackingService
import com.sijanneupane.runningtracker.ui.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_tracking.*

@AndroidEntryPoint
class TrackingFragment : Fragment(R.layout.fragment_tracking) {

    //get viewmodel from dagger
    private val viewModel: MainViewModel by viewModels()

    private var isTracking= false
    private var pathPoints= mutableListOf<Polyline>()
    private  var map: GoogleMap? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mapView.onCreate(savedInstanceState)

        btnToggleRun.setOnClickListener{
        toggleRun()
        }

        mapView.getMapAsync{
            map= it
            addAllPolylines()
        }

        subscribeToObservers()
    }



    private fun sendCommandToService(action: String) =
        Intent(requireContext(), TrackingService::class.java).also {
            it.action = action
            requireContext().startService(it)
        }

    private fun addLatestPolyline(){
        if (pathPoints.isNotEmpty() && pathPoints.last().size > 1){
            var preLastLang = pathPoints.last()[pathPoints.last().size-2]
            val lastLatLang= pathPoints.last().last()

            val polylineOptions = PolylineOptions()
                .color(POLYLINE_COLOR)
                .width(POLYLINE_WIDTH)
                .add(preLastLang)
                .add(lastLatLang)
            map?.addPolyline(polylineOptions)

        }
    }

    private fun addAllPolylines(){
        for (polyline in pathPoints){
            val polylineOptions= PolylineOptions()
                .color(POLYLINE_COLOR)
                .width(POLYLINE_WIDTH)
                .addAll(polyline)
            map?.addPolyline(polylineOptions)
        }
    }

    //Move camera to User
    private fun moveCameraToUser(){
        if (pathPoints.isNotEmpty() && pathPoints.last().isNotEmpty()){
            map?.animateCamera(
                CameraUpdateFactory.newLatLngZoom(
                    pathPoints.last().last(),
                    MAP_ZOOM
                )
            )
        }
    }

    //observe data from the service and react to those changes
    private fun updateTracking(isTracking: Boolean){
        this.isTracking= isTracking
        if (!isTracking){
            //PAUSE STATE
            btnToggleRun.text= "Start"
            btnFinishRun.visibility= View.VISIBLE
        }else{
            //TRACKING STATE
            btnToggleRun.text= "Stop"
            btnFinishRun.visibility= View.GONE
        }
    }

    // TOGGLE THE SERVICE IF START OR PAUSE OR STOP
    private fun toggleRun(){
        if (isTracking){
            sendCommandToService(ACTION_PAUSE_SERVICE)
        }else{
            sendCommandToService(ACTION_START_OR_RESUME_SERVICE)
        }
    }

    //subscribe to the live data objects
    private fun subscribeToObservers(){
        TrackingService.isTracking.observe(viewLifecycleOwner, Observer {
            updateTracking(it)
        })
        TrackingService.pathPoints.observe(viewLifecycleOwner, Observer {
            pathPoints= it
            addLatestPolyline()
            moveCameraToUser()
        })
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