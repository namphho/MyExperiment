package com.example.myexperiment.experiments.firebase.authrepo

import com.example.myexperiment.experiments.firebase.model.User
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

/**
 * Created by nampham on 6/1/21.
 */
class FirebaseAuthImpl(private val fireAuth: FirebaseAuth) : Authentication {
    override suspend fun login(email: String, password: String): User? {
        return try {
            val data = fireAuth.signInWithEmailAndPassword(email, password).await()

            if (data.user != null) {
                User(data.user?.email ?: "")
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun signUp(email: String, password: String) : Boolean{
        return try {
            val data = fireAuth.createUserWithEmailAndPassword(email, password).await()
            data.user != null
        } catch (e : Exception) {
            false
        }
    }

    override fun isSignedIn(): Boolean {
        return fireAuth.currentUser != null
    }

}