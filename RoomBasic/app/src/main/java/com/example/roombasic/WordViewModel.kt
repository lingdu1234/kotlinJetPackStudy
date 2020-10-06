package com.example.roombasic

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData


class WordViewModel(application: Application) : AndroidViewModel(application) {
    val wordDao: WordDao? = WordDatabase.getDatabase(application)?.wordDao()
    val allWordsLive: LiveData<List<Word>> = wordDao!!.getAllWordsLive()
}