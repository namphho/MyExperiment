package com.example.myexperiment.experiments.firebase.screens.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myexperiment.experiments.firebase.datarepo.FirestoreRepository
import kotlinx.coroutines.launch

/**
 * Created by nampham on 6/11/21.
 */

class AddUserViewModel(private val fs : FirestoreRepository) : ViewModel(){
    private var _isLoading : MutableLiveData<Boolean> = MutableLiveData()
    val isLoading : LiveData<Boolean>
        get() = _isLoading

    private var _isAddSuccess : MutableLiveData<Boolean> = MutableLiveData()
    val isAddSuccess : LiveData<Boolean>
        get() = _isAddSuccess

    fun addUser(email: String, fullName: String, clazzYear: String) {
        viewModelScope.launch {
            _isLoading.value = true
            val resp = fs.addStudent(email = email, fullName= fullName, clazzYear = clazzYear.toInt())
            _isLoading.value = false
            _isAddSuccess.value = resp
        }
    }

}