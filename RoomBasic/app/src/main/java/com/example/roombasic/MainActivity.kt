package com.example.roombasic

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.CompoundButton
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.cell_card_2.*
import kotlinx.android.synthetic.main.cell_nomal_2.*
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    /*    var buttonInsert: Button? = null
        var buttonClear: Button? = null
        var wordViewModel: WordViewModel? = null
        var recyclerView: RecyclerView? = null
        var aSwitch: Switch? = null
        var myAdapter1: MyAdapter? = null
        var myAdapter2: MyAdapter? = null*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //wordViewModel = ViewModelProviders.of(this).get(WordViewModel.class);
        val wordViewModel = ViewModelProvider(this).get(WordViewModel::class.java)
        val myAdapter1 = MyAdapter(false, wordViewModel)
        val myAdapter2 = MyAdapter(true, wordViewModel)
        recyclerView.layoutManager = LinearLayoutManager(this)

        recyclerView.adapter = myAdapter1
        switchStyle.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                recyclerView.adapter = myAdapter2
                Log.d("MyTag","AAA $wordViewModel")
            } else {
                recyclerView.adapter = myAdapter1
            }
        }
        wordViewModel.allWordsLive.observe(this, { words ->
            val temp = myAdapter1.itemCount
            myAdapter1.allWords = words
            myAdapter2.allWords = words
            if (temp != words.size) {
                myAdapter1.notifyDataSetChanged()
                myAdapter2.notifyDataSetChanged()
            }
        })
        buttonInsert.setOnClickListener(View.OnClickListener {
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
                thread {
                    wordViewModel.wordDao?.insertWords(Word(english[i], chinese[i]))
                }
            }
        })
        buttonClear?.setOnClickListener {
            thread {
                wordViewModel.wordDao?.deleteAllWords()
            }
        }
    }
}