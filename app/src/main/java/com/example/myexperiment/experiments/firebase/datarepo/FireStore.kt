package com.example.myexperiment.experiments.firebase.datarepo

import com.example.myexperiment.experiments.firebase.model.Student

/**
 * Created by nampham on 6/11/21.
 */
interface FireStore {
    //get
    suspend fun getStudents() : List<Student?>

    //add user info
    suspend fun addStudent(fullName: String, email: String, clazzYear: Int) : Boolean
}