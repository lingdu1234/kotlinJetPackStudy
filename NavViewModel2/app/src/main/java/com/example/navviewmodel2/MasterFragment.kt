package com.example.navviewmodel2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.navviewmodel2.databinding.FragmentMasterBinding

class MasterFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?):View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_master, container, false)
        val myViewModel = ViewModelProvider(this).get(MyViewModel::class.java)
        val binding = DataBindingUtil.inflate<FragmentMasterBinding>(inflater, R.layout.fragment_master, container, false)

/*        binding.button.setOnClickListener(View.OnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_masterFragment_to_detailFragment)
        })*/
        binding.data = myViewModel
        binding.lifecycleOwner = this
        binding.button.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_masterFragment_to_detailFragment))
        binding.seekBar.progress = myViewModel.number.value!!
        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                myViewModel.number.value = progress
                println(myViewModel.number.value!!)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })

        return binding.root
    }



}