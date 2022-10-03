package com.sijanneupane.runningtracker.other

import android.content.Context
import android.icu.util.Calendar
import android.os.Build
import androidx.annotation.RequiresApi
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.utils.MPPointF
import com.sijanneupane.runningtracker.database.Run
import kotlinx.android.synthetic.main.marker_view.view.*
import java.text.SimpleDateFormat
import java.util.*

class CustomMarkerView(
    val runs: List<Run>,
    c: Context,
    layoutId: Int
): MarkerView(c, layoutId) {

    override fun getOffset(): MPPointF {
        return MPPointF(-width/2f, -height.toFloat())
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun refreshContent(e: Entry?, highlight: Highlight?) {
        super.refreshContent(e, highlight)
        if (e==null){
            return
        }
        val currRunId= e.x.toInt()
        val run= runs[currRunId]

        val calendar= Calendar.getInstance().apply {
            timeInMillis= run.timeStamp
        }
        val dateformat= SimpleDateFormat("dd.MM.yy", Locale.getDefault())
        tvDate.text= dateformat.format(calendar.time)

        val averageSpeed= "${run.averageSpeedInKMH}km/h"
        tvAvgSpeed.text= averageSpeed

        val distanceInKM= "${run.distanceInMeters/1000f}km"
        tvDistance.text= distanceInKM

        tvDuration.text= TrackingUtility.getFormattedStopWatchTime(run.timeInMillis)

        val caloriesBurned= "${run.caloriesBurned}KCal"
        tvCaloriesBurned.text= caloriesBurned
    }
}