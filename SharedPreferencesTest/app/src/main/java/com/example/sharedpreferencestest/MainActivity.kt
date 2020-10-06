package com.example.sharedpreferencestest

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val myData = MyData(applicationContext)//不能用this
        myData.save()
/*        val shp: SharedPreferences? = getSharedPreferences("MY_DATA", MODE_PRIVATE)
        val edit = shp?.edit()
        edit?.putInt("NUM",200)
        edit?.apply()*/
        val x = myData.load()
        val tag: String = "myLog"
        Log.d(tag, "消息$x")

    }
}