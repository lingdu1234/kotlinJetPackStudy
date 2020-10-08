package com.example.gallery

import android.os.Bundle
import android.os.Handler
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.fragment_gallery.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class GalleryFragment : Fragment() {
    private lateinit var galleryViewModel: GalleryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_gallery, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.swipeIndcator -> {
                swipeLayoutGallery.isRefreshing = true
                GlobalScope.launch {
                    delay(1000)
                    galleryViewModel.fetchData()
                }

            }
        }
        return super.onOptionsItemSelected(item)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)
        val galleyAdapter = GalleyAdapter()
        recyclerView.apply {
            adapter = galleyAdapter
//            layoutManager = GridLayoutManager(requireContext(), 2)
            layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        }
        galleryViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory(requireActivity().application)
        ).get(GalleryViewModel::class.java)
        galleryViewModel.photoListLive.observe(this, Observer {
            galleyAdapter.submitList(it)
            swipeLayoutGallery.isRefreshing = false
        })
        galleryViewModel.photoListLive.value ?: galleryViewModel.fetchData()
        swipeLayoutGallery.setOnRefreshListener {
            galleryViewModel.fetchData()
        }
    }
}