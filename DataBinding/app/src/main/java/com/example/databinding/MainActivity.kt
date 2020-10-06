package com.example.databinding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.databinding.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var myViewModel: MyViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding:ActivityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)
//        setContentView(R.layout.activity_main)
        myViewModel = ViewModelProvider(this)[MyViewModel::class.java]
//        myViewModel.number.observe(this, Observer {
//            textView.text = it.toString()
//        })
//        button.setOnClickListener(View.OnClickListener {
//            myViewModel.addLike()
//        })
        binding.data = myViewModel
        binding.lifecycleOwner = this
    }
}