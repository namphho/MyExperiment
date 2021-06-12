package com.example.myexperiment.experiments.room.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by nampham on 5/13/21.
 */
@Entity(tableName = "words")
class Word(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name="word") val word : String
)
