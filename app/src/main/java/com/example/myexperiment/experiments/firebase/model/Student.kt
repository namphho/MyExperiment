package com.example.myexperiment.experiments.firebase.model

/**
 * Created by nampham on 6/11/21.
 */
class Student(val email: String, val fullName: String, val clazzYear: Int) {
    constructor() : this("", "", 0)

    override fun toString(): String {
        return "Student(email='$email', fullName='$fullName', clazzYear=$clazzYear)"
    }

}