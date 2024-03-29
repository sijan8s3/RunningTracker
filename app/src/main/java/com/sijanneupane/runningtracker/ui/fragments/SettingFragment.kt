package com.sijanneupane.runningtracker.ui.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.sijanneupane.runningtracker.R
import com.sijanneupane.runningtracker.other.Constants.KEY_NAME
import com.sijanneupane.runningtracker.other.Constants.KEY_WEIGHT
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_settings.*
import javax.inject.Inject

@AndroidEntryPoint
class SettingFragment : Fragment(R.layout.fragment_settings) {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadFieldsFromSharedPrefs()

        btnApplyChanges.setOnClickListener {
            val success= applyChangesToSharedPref()
            if (success){
                Snackbar.make(view, "Changes Saved!", Snackbar.LENGTH_LONG).show()
            }else{
                Snackbar.make(view, "Please fill all fields.", Snackbar.LENGTH_LONG).show()
            }
        }
    }


    private fun loadFieldsFromSharedPrefs(){
        val name= sharedPreferences.getString(KEY_NAME, "")
        val weight= sharedPreferences.getFloat(KEY_WEIGHT, 60f)
        etName.setText(name)
        etWeight.setText(weight.toString())
    }

    private fun applyChangesToSharedPref(): Boolean {
        val nameText = etName.text.toString()
        val weightText = etWeight.text.toString()
        if (nameText.isEmpty() || weightText.isEmpty()) {
            return false
        }
        sharedPreferences.edit()
            .putString(KEY_NAME, nameText)
            .putFloat(KEY_WEIGHT, weightText.toFloat())
            .apply()

        val toolbarText = "Let's go, $nameText."
        requireActivity().tvToolbarTitle.text = toolbarText
        return true
    }
}