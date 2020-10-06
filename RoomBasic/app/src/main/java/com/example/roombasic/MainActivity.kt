package com.example.roombasic

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    //    private lateinit var wordDao: WordDao
//    lateinit var allWordsLive: LiveData<List<Word>>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//  val  recyclerview = findViewById<View>(R.id.recyclerview)
        recyclerview.layoutManager = LinearLayoutManager(this)
        val myAdapter1 = MyAdapter(false)
        val myAdapter2 = MyAdapter(true)
        recyclerview.adapter = myAdapter1

        buttonSwitch.setOnCheckedChangeListener { _, isChecked ->
            recyclerview.adapter = if (isChecked) myAdapter2 else myAdapter1
        }

        /*{
            isUseCardView = !isUseCardView
            println(isUseCardView)
            recyclerview.adapter = myAdapter
        }*/


/*        val wordDatabase = Room.databaseBuilder(this, WordDatabase::class.java, "word_database")
            .allowMainThreadQueries().build()*/
//        val wordDao = WordDatabase.getDatabase(this)?.wordDao()
//        allWordsLive = wordDao!!.getAllWordsLive()
//        updateView()
        val wordViewModel = ViewModelProvider(this)[WordViewModel::class.java]
        wordViewModel.allWordsLive.observe(this,
            {
                myAdapter1.allWords = it
                myAdapter2.allWords = it
                myAdapter1.notifyDataSetChanged()
                myAdapter2.notifyDataSetChanged()
/*                words ->
            val text: StringBuilder = StringBuilder()
            for (word in words!!) {
                text.append("${word.id} : ${word.englishWord} = ${word.chineseMeaning}\n")

            }
            textView.text = text*/
            })
        buttonInsert.setOnClickListener(View.OnClickListener {
            thread {
                val english = arrayOf(
                    "Hello",
                    "World",
                    "Android",
                    "Google",
                    "Studio",
                    "Project",
                    "Database",
                    "Recycler",
                    "View",
                    "String",
                    "Value",
                    "Integer"
                )
                val chinese = arrayOf(
                    "你好",
                    "世界",
                    "安卓系统",
                    "谷歌公司",
                    "工作室",
                    "项目",
                    "数据库",
                    "回收站",
                    "视图",
                    "字符串",
                    "价值",
                    "整数类型"
                )
                for (i in english.indices) {
                    wordViewModel.wordDao?.insertWords(Word(english[i], chinese[i]))
                }
/*                val word1 = Word("hello", "你好")
                val word2 = Word("world", "世界")
                wordViewModel.wordDao?.insertWords(word1, word2)*/
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