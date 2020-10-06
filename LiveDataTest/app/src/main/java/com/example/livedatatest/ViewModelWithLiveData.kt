package com.example.livedatatest

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ViewModelWithLiveData: ViewModel() {
    val likedNumber: MutableLiveData<Int>
    get() = _likeNumber
    private  val _likeNumber = MutableLiveData<Int>()

    fun addLike(n: Int) {
        val i = likedNumber.value ?: 0
        _likeNumber.value = i + n
    }

}