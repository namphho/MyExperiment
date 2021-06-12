package com.example.myexperiment.experiments.room

import androidx.lifecycle.*
import com.example.myexperiment.experiments.room.db.Word
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

/**
 * Created by nampham on 5/15/21.
 */
class RoomExampleViewModel(private val wordRepo: WordRepository) : ViewModel(){
    private var _words : MutableLiveData<List<Word>> = MutableLiveData()
    val words : LiveData<List<Word>>
        get() = _words

    fun getAllWords() {
        viewModelScope.launch {
            val result = wordRepo.getAllWord()
            _words.postValue(result)
        }
    }

    fun insert(word: Word) = viewModelScope.launch{
        wordRepo.insert(word)
        val result = wordRepo.getAllWord()
        _words.postValue(result)
    }
}

class WordViewModelFactory(val repository: WordRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RoomExampleViewModel::class.java)) {
            return RoomExampleViewModel(repository) as T
        }
        throw IllegalArgumentException("unknown view model")
    }

}