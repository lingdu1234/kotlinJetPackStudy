package com.example.sharedpreferencestest

import android.content.Context
import android.content.SharedPreferences

class MyData(private var context: Context) {
    private val key: String = context.getString(R.string.MY_KEY)
    private val data: String = context.getString(R.string.MY_DATA)
    private val shp: SharedPreferences = context.getSharedPreferences(data, Context.MODE_PRIVATE)

    fun save() {
        with(shp.edit()) {
            putInt(key, 111223)
            apply()
        }
    }

    fun load(): Int = shp.getInt(key, 0)
}