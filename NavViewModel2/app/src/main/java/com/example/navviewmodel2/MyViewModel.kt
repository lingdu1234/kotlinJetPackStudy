package com.example.navviewmodel2

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel() {
/*    val number: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>().also {
            it.value = 0
        }
    }*/
val number: MutableLiveData<Int> = MutableLiveData<Int>()

    init {
        number.value = 0
    }

    fun add(x: Int) {
        number.value = number.value?.plus(x)
        println(number.value)

    }


}

