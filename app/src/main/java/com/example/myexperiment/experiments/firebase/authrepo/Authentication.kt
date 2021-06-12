package com.example.myexperiment.experiments.firebase.authrepo

import com.example.myexperiment.experiments.firebase.model.User

/**
 * Created by nampham on 6/1/21.
 */
interface Authentication {
    suspend fun login(email: String, password: String) : User?

    suspend fun signUp(email: String, password: String) : Boolean

    fun isSignedIn() : Boolean
}