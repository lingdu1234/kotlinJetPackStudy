package com.example.databinding

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel() {
   val number: MutableLiveData<Int>
      get() = _number
   private  val _number = MutableLiveData<Int>()

   fun addLike() {
      val i = number.value ?: 0
      _number.value = i + 1
   }
}