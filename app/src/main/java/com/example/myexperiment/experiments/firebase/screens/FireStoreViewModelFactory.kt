package com.example.myexperiment.experiments.firebase.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myexperiment.experiments.firebase.authrepo.AuthRepository
import com.example.myexperiment.experiments.firebase.datarepo.FirestoreRepository
import com.example.myexperiment.experiments.firebase.screens.add.AddUserViewModel
import com.example.myexperiment.experiments.firebase.screens.detail.DetailFirebaseViewModel
import com.example.myexperiment.experiments.firebase.screens.login.FirebaseLoginViewModel
import com.example.myexperiment.experiments.firebase.screens.register.FirebaseRegisterViewModel
import com.example.myexperiment.experiments.room.RoomExampleViewModel
import java.lang.IllegalArgumentException

/**
 * Created by nampham on 6/2/21.
 */
class FireStoreViewModelFactory(val repo: FirestoreRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddUserViewModel::class.java)) {
            return AddUserViewModel(repo) as T
        } else if (modelClass.isAssignableFrom(DetailFirebaseViewModel::class.java)) {
            return DetailFirebaseViewModel(repo) as T
        }
        throw IllegalArgumentException("unknown view model")
    }

}