package com.example.myexperiment.experiments.firebase.datarepo

import android.util.Log
import com.example.myexperiment.experiments.firebase.model.Student
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import java.lang.Exception

/**
 * Created by nampham on 6/11/21.
 */
class FireStoreImpl(private val db: FirebaseFirestore) : FireStore {
    companion object {
        private val TAG = FireStoreImpl::class.java.simpleName
    }

    override suspend fun getStudents(): List<Student?> {
        return try {
            val resp = db.collection("users").get().await()
            resp.documents.map {
                it.toObject(Student::class.java)
            }.toList()
        } catch (e : Exception) {
            Log.e(TAG, e.message.toString())
            return emptyList()
        }
    }

    override suspend fun addStudent(fullName: String, email: String, clazzYear: Int): Boolean {
        return try {
            val user = hashMapOf(
                "fullName" to fullName,
                "email" to email,
                "clazzYear" to clazzYear
            )
            val data = db.collection("users").add(user).await() //generate user id
            true
        } catch (e : Exception){
            false
        }

    }
}