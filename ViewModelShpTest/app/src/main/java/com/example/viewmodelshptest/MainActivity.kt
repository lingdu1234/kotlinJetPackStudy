package com.example.viewmodelshptest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.viewmodelshptest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var myViewModel: MyViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//        val myViewModel = MyViewModel(application,)
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
//      val myViewModel =  ViewModelProvider(this)[MyViewModel::class.java]
        myViewModel = ViewModelProvider(this)[MyViewModel::class.java]
        binding.myData = myViewModel
        binding.lifecycleOwner = this
    }

    override fun onPause() {
        super.onPause()
        myViewModel.saved()
    }

}