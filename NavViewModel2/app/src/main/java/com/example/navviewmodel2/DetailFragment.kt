package com.example.navviewmodel2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.navviewmodel2.databinding.FragmentDetailBinding


class DetailFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_detail, container, false)
        val myViewModel = ViewModelProvider(this).get(MyViewModel::class.java)
        val binding = DataBindingUtil.inflate<FragmentDetailBinding>(
            inflater,
            R.layout.fragment_detail,
            container,
            false
        )
        binding.data = myViewModel
        binding.lifecycleOwner = this

        binding.button4.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_detailFragment_to_masterFragment))

        return binding.root
    }
}