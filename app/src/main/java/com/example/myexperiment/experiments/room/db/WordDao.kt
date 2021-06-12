package com.example.myexperiment.experiments.room.db

import androidx.room.*

/**
 * Created by nampham on 5/13/21.
 */
@Dao
interface WordDao {

    @Query("SELECT * FROM words ORDER BY word ASC")
    suspend fun getAlphabetizedWords(): List<Word>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(word: Word)

    @Query("DELETE FROM words")
    suspend fun deleteAll()

    @Delete
    suspend fun delete(word: Word)

    @Update
    suspend fun update(word: Word)
}

//view database