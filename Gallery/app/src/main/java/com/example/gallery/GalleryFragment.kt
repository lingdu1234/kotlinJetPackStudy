package com.example.gallery

//import android.content.pm.ActivityInfo
//import android.os.Handler
//import kotlinx.coroutines.GlobalScope
import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.fragment_gallery.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class GalleryFragment : Fragment() {
    //    private lateinit var galleryViewModel: GalleryViewModel
    private val galleryViewModel: GalleryViewModel by viewModels<GalleryViewModel>()

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
/*                GlobalScope.launch {
                    delay(1000)
                    galleryViewModel.resetQuery()
                }*/
//                Handler().postDelayed({ galleryViewModel.resetQuery() },1000)
                viewLifecycleOwner.lifecycleScope.launch {
                    delay(1000)
                    galleryViewModel.resetQuery()
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
//            ORIENTATION_LANDSCAPE
// 根据屏幕方向设置界面
            layoutManager =    when{
                (context.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) -> StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                else -> StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
            }

        }
        galleryViewModel.pagedListLiveData.observe(viewLifecycleOwner, Observer {
            galleyAdapter.submitList(it)
            swipeLayoutGallery.isRefreshing = false
        })
        swipeLayoutGallery.setOnRefreshListener {
            galleryViewModel.resetQuery()
        }
/*        galleryViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory(requireActivity().application)
        ).get(GalleryViewModel::class.java)*/


/*        galleryViewModel.photoListLive.observe(this, Observer {
            galleyAdapter.submitList(it)
            swipeLayoutGallery.isRefreshing = false
        })
        galleryViewModel.photoListLive.value ?: galleryViewModel.fetchData()
        swipeLayoutGallery.setOnRefreshListener {
            galleryViewModel.fetchData()
        }*/
    }
}