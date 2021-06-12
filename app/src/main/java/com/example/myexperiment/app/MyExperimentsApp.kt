package com.example.myexperiment.app

import android.app.Application
import com.example.myexperiment.experiments.firebase.authrepo.AuthRepository
import com.example.myexperiment.experiments.firebase.authrepo.FirebaseAuthImpl
import com.example.myexperiment.experiments.firebase.datarepo.FireStoreImpl
import com.example.myexperiment.experiments.firebase.datarepo.FirestoreRepository
import com.example.myexperiment.experiments.room.WordRepository
import com.example.myexperiment.experiments.room.db.WordRoomDatabase
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

/**
 * Created by nampham on 5/15/21.
 */
class MyExperimentsApp : Application() {

    val database by lazy { WordRoomDatabase.getDatabase(this)}
    val repository by lazy { WordRepository(database.wordDao()) }

    val fireAuthentication by lazy { FirebaseAuthImpl(Firebase.auth) }
    val authRepository by lazy { AuthRepository(fireAuthentication) }

    val fireStoreImpl : FireStoreImpl by lazy { FireStoreImpl(Firebase.firestore) }
    val fireStoreRepo : FirestoreRepository by lazy { FirestoreRepository(fireStoreImpl)}

    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(applicationContext)
    }
}