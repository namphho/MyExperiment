package com.example.myexperiment.experiments.firebase.screens.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myexperiment.experiments.firebase.authrepo.AuthRepository
import kotlinx.coroutines.launch

/**
 * Created by nampham on 6/2/21.
 */
class FirebaseRegisterViewModel(private val authRepository: AuthRepository) : ViewModel() {

    private var _isSignUpSuccess : MutableLiveData<Boolean> = MutableLiveData()
    val isSignUpSuccess : LiveData<Boolean>
        get() = _isSignUpSuccess

    private var _isLoading : MutableLiveData<Boolean> = MutableLiveData()
    val isLoading : LiveData<Boolean>
        get() = _isLoading

    fun signUp(email: String, pass: String) {
        viewModelScope.launch {
            _isLoading.value = true
            val isSuccess = authRepository.signUp(email, pass)
            _isSignUpSuccess.value = isSuccess
            _isLoading.value = false
        }
    }
}