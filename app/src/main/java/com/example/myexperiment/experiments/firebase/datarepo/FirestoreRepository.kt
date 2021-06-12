package com.example.myexperiment.experiments.firebase.datarepo

import com.example.myexperiment.experiments.firebase.model.Student
import java.time.Year

/**
 * Created by nampham on 6/11/21.
 */
class FirestoreRepository(private val fs : FireStore) {
    suspend fun addStudent(email: String, fullName: String, clazzYear: Int) : Boolean{
        return fs.addStudent(email = email, fullName = fullName, clazzYear = clazzYear)
    }

    suspend fun getStudents() : List<Student?>{
        return fs.getStudents()
    }
}