package com.example.livedatatest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var viewModelDataWithLiveData: ViewModelWithLiveData
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        imageButton1.setOnClickListener(this)
        imageButton2.setOnClickListener(this)
        viewModelDataWithLiveData = ViewModelProvider(this)[ViewModelWithLiveData::class.java]
        viewModelDataWithLiveData.likedNumber.observe(this, Observer {
            textView1.text = it.toString()
        })
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.imageButton1-> viewModelDataWithLiveData.addLike(1)
            R.id.imageButton2 -> viewModelDataWithLiveData.addLike(-1)

        }
    }
}