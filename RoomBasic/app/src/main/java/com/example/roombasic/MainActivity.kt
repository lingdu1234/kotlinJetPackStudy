package com.example.roombasic

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
//    private lateinit var wordDao: WordDao
//    lateinit var allWordsLive: LiveData<List<Word>>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
/*        val wordDatabase = Room.databaseBuilder(this, WordDatabase::class.java, "word_database")
            .allowMainThreadQueries().build()*/
//        val wordDao = WordDatabase.getDatabase(this)?.wordDao()
//        allWordsLive = wordDao!!.getAllWordsLive()
//        updateView()
    val wordViewModel = ViewModelProvider(this)[WordViewModel::class.java]
    wordViewModel.allWordsLive.observe(this,
            { words ->
                val text: StringBuilder = StringBuilder()
                for (word in words!!) {
                    text.append("${word.id} : ${word.word} = ${word.chineseMeaning}\n")

                }
                textView.text = text
            })
        buttonInsert.setOnClickListener(View.OnClickListener {
            thread {

                val word1 = Word("hello", "你好")
                val word2 = Word("world", "世界")
                wordViewModel.wordDao?.insertWords(word1, word2)
            }
//            updateView()

        })
//        buttonSearch.setOnClickListener { updateView() }
        buttonClear.setOnClickListener {
            thread {
                wordViewModel.wordDao?.deleteAllWords()
            }
//            updateView()
        }
        buttonUpdate.setOnClickListener {
            thread {
                val word: Word = Word("hi", "你哈皮啊")
                word.id = 36
                wordViewModel.wordDao?.updateWords(word)
            }
//            updateView()

        }
        buttonDelete.setOnClickListener {
            thread {
                val word: Word = Word("hi", "你哈皮啊")
                word.id = 42
                wordViewModel.wordDao?.deleteWords(word)
            }
        }
    }

/*    private fun updateView() {
        val list = wordDao.getAllWordsLive()
        var text: String = ""
        for (word in list) {
            text += "${word.id} : ${word.word} = ${word.chineseMeaning}\n"

        }
        textView.text = text

    }*/
}