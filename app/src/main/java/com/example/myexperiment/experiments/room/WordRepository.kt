package com.example.myexperiment.experiments.room

import androidx.annotation.WorkerThread
import com.example.myexperiment.experiments.room.db.Word
import com.example.myexperiment.experiments.room.db.WordDao

/**
 * Created by nampham on 5/15/21.
 */
// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class WordRepository (private val wordDao: WordDao) {

    // Room executes all queries on a separate thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun getAllWord() : List<Word> {
        return wordDao.getAlphabetizedWords()
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(word: Word){
        wordDao.insert(word)
    }
}