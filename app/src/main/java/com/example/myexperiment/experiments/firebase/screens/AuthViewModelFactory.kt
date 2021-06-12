package com.example.myexperiment.experiments.firebase.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myexperiment.experiments.firebase.authrepo.AuthRepository
import com.example.myexperiment.experiments.firebase.screens.login.FirebaseLoginViewModel
import com.example.myexperiment.experiments.firebase.screens.register.FirebaseRegisterViewModel
import com.example.myexperiment.experiments.room.RoomExampleViewModel
import java.lang.IllegalArgumentException

/**
 * Created by nampham on 6/2/21.
 */
class AuthViewModelFactory(val repo: AuthRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FirebaseLoginViewModel::class.java)) {
            return FirebaseLoginViewModel(repo) as T
        } else if (modelClass.isAssignableFrom(FirebaseRegisterViewModel::class.java)) {
            return FirebaseRegisterViewModel(repo) as T
        }
        throw IllegalArgumentException("unknown view model")
    }

}