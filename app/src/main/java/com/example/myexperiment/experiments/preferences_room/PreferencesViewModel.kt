package com.example.myexperiment.experiments.preferences_room

import android.content.SharedPreferences
import android.util.Log
import androidx.core.content.edit
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Created by nampham on 5/10/21.
 */
class PreferencesViewModel(val sharedPref: SharedPreferences) : ViewModel() {
    companion object {
        private val TAG = PreferencesViewModel::class.java.simpleName
        private val TEXT_KEY = "TEXT"
    }

    var inputText: MutableLiveData<String> = MutableLiveData<String>()

    var _receivedValue: MutableLiveData<String> = MutableLiveData<String>()
    val receivedValue: LiveData<String>
        get() = _receivedValue

    fun handleSaveValue() {
        val input = inputText.value
        sharedPref.edit {
            putString(TEXT_KEY, input)
        }
    }

    fun handleReceived() {
        val result = sharedPref.getString(TEXT_KEY, "")
        _receivedValue.postValue(result)
    }
}


class PreferenceViewModelFactory(private val sharedPref: SharedPreferences) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PreferencesViewModel::class.java)) {
            return PreferencesViewModel(sharedPref) as T
        }
        throw IllegalArgumentException("unknown viewmodel class")
    }

}