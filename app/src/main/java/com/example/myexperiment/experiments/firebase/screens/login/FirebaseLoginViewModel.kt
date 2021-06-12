package com.example.myexperiment.experiments.firebase.screens.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myexperiment.experiments.firebase.authrepo.AuthRepository
import kotlinx.coroutines.launch

/**
 * Created by nampham on 6/1/21.
 */
class FirebaseLoginViewModel(val authRepo: AuthRepository) : ViewModel() {
    companion object {
        private val TAG = FirebaseLoginViewModel::class.java.simpleName
    }

    private val _isLoginSuccess : MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    val isLoginSuccess: LiveData<Boolean>
        get() = _isLoginSuccess

    private var _isLoading : MutableLiveData<Boolean> = MutableLiveData()
    val isLoading : LiveData<Boolean>
        get() = _isLoading

    fun login(email : String, password: String){
        viewModelScope.launch {
            _isLoading.value = true
            val user = authRepo.login(email, password)
            _isLoginSuccess.value = user != null
            _isLoading.value = false
        }
    }

    fun isLogged() : Boolean {
        return authRepo.isLogged()
    }
}