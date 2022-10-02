package com.sijanneupane.runningtracker.adapters

import android.icu.util.Calendar
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sijanneupane.runningtracker.R
import com.sijanneupane.runningtracker.database.Run
import com.sijanneupane.runningtracker.other.TrackingUtility
import kotlinx.android.synthetic.main.item_run.view.*
import java.text.SimpleDateFormat
import java.util.*

class RunAdapter: RecyclerView.Adapter<RunAdapter.RunViewHolder>() {

    inner class RunViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    val diffCallback= object: DiffUtil.ItemCallback<Run>() {
        override fun areItemsTheSame(oldItem: Run, newItem: Run): Boolean {
            return oldItem.id== newItem.id
        }

        override fun areContentsTheSame(oldItem: Run, newItem: Run): Boolean {
            return oldItem.hashCode()== newItem.hashCode()
        }

    }

    val differ= AsyncListDiffer(this, diffCallback)

    fun submitList(list:List<Run>)= differ.submitList(list)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RunViewHolder {
        return RunViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_run,
                parent,
                false
            )
        )
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(holder: RunViewHolder, position: Int) {
        val run =differ.currentList[position]
        holder.itemView.apply {
            Glide.with(this).load(run.img).into(ivRunImage)
            val calendar= Calendar.getInstance().apply {
                timeInMillis= run.timeStamp
            }
            val dateformat= SimpleDateFormat("dd.MM.yy", Locale.getDefault())
            tvDate.text= dateformat.format(calendar.time)

            val averageSpeed= "${run.averageSpeedInKMH}km/h"
            tvAvgSpeed.text= averageSpeed

            val distanceInKM= "${run.distanceInMeters/1000f}km"
            tvDistance.text= distanceInKM

            tvTime.text= TrackingUtility.getFormattedStopWatchTime(run.timeInMillis)

            val caloriesBurned= "${run.caloriesBurned}KCal"
            tvCalories.text= caloriesBurned
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


}