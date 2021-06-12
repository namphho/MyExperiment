package com.example.myexperiment.experiments.firebase.authrepo

import com.example.myexperiment.experiments.firebase.model.User

/**
 * Created by nampham on 6/1/21.
 */
class AuthRepository(private val auth : Authentication) {

    suspend fun login(email : String, password: String) : User? {
        return auth.login(email, password)
    }

    suspend fun signUp(email : String, password: String) : Boolean{
        return auth.signUp(email, password)
    }

    fun isLogged() : Boolean {
        return auth.isSignedIn()
    }
}