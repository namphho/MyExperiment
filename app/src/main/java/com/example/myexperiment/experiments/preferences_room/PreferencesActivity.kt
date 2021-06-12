package com.example.myexperiment.experiments.preferences_room

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.edit
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.myexperiment.R
import com.example.myexperiment.databinding.ActivityPreferencesBinding

class PreferencesActivity : AppCompatActivity() {
    companion object {
        private val TAG = PreferencesActivity::class.java.simpleName
    }
    lateinit var binding: ActivityPreferencesBinding
    lateinit var viewModel: PreferencesViewModel
    lateinit var sharedPref: SharedPreferences



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPref = getSharedPreferences("NAME", Context.MODE_PRIVATE)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_preferences)
        viewModel = ViewModelProvider(this, PreferenceViewModelFactory(sharedPref)).get(PreferencesViewModel::class.java)
        binding.lifecycleOwner = this
        binding.model = viewModel

        //preferences for this activity
        //val pref = getPreferences(Context.MODE_PRIVATE)
    }
}