package com.example.viewmodelshptest

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle

class MyViewModel(application: Application, var handle: SavedStateHandle) :
    AndroidViewModel(application) {
    //    var key = getApplication<Application>().resources.getString(R.string.data_key)
    private val key = application.getString(R.string.data_key)

    //    var shpName = getApplication<Application>().resources.getString(R.string.shp_name)
    private val shpName = application.getString(R.string.shp_name)
    val number: LiveData<Int>
        get() = handle.getLiveData(key)
    private val shp: SharedPreferences =
        getApplication<Application>().getSharedPreferences(shpName, Context.MODE_PRIVATE)


    private fun load() {
        val x = shp.getInt(key, 0)
        handle.set(key, x)
    }

    fun saved() {

        with(shp.edit()) {
            putInt(key, number.value!!)
            apply()
        }
    }

    fun add(x: Int) {
        handle.set(key, number.value!! + x)
//        saved()
    }

    init {
        if (!handle.contains(key)) {
            load()
        }
    }
}