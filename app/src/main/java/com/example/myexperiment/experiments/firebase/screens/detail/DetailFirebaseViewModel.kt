package com.example.myexperiment.experiments.firebase.screens.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myexperiment.experiments.firebase.datarepo.FirestoreRepository
import kotlinx.coroutines.launch

/**
 * Created by nampham on 6/11/21.
 */
class DetailFirebaseViewModel(private val db: FirestoreRepository) : ViewModel() {
    companion object {
        private val TAG = DetailFirebaseViewModel::class.java.simpleName
    }

    private var _isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    fun loadStudents() {
        viewModelScope.launch {
            _isLoading.value = true
            val user = db.getStudents()
            _isLoading.value = false
            Log.e(TAG, user.toString())
        }
    }
}