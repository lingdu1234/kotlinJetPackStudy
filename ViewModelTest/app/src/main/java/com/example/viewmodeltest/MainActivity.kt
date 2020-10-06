package com.example.viewmodeltest

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var myViewModel: MyViewModel
//    private lateinit var textView:TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        myViewModel = ViewModelProvider(this)[MyViewModel::class.java]
        b



    }

    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }

}