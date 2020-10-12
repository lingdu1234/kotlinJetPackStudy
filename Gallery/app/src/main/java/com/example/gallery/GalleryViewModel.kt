package com.example.gallery

import android.app.Application
import androidx.paging.toLiveData
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations

//import android.util.Log
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import com.android.volley.Request
//import com.android.volley.Response
//import com.android.volley.toolbox.StringRequest
//import com.google.gson.Gson

class GalleryViewModel(application: Application) : AndroidViewModel(application) {
    private val factory = PixabayDataSourceFactory(application)
    val pagedListLiveData = factory.toLiveData(1)
    val networkStatus: LiveData<NetworkStatus> = Transformations.switchMap(factory.pixabayDataSource) { it.networkStatus }
    fun resetQuery(){
        pagedListLiveData.value?.dataSource?.invalidate()
    }
    fun retry(){
        factory.pixabayDataSource.value?.retry?.invoke()
    }




/*    private val _photoListLive = MutableLiveData<List<PhotoItem>>()
    val photoListLive: LiveData<List<PhotoItem>> get() = _photoListLive
    fun fetchData() {
        val stringRequest = StringRequest(
            Request.Method.GET,
            getUrl(),
            Response.Listener {
                _photoListLive.value = Gson().fromJson(it, Pixabay::class.java).hits.toList()
            },
            Response.ErrorListener {
                Log.d("myTag", it.toString())
            }
        )
        VolleySingleton.getInstance(getApplication()).requestQueue.add(stringRequest)

    }

    private fun getUrl(): String {
//        https://pixabay.com/api/?key=18611589-dbade0af15519d715c91bde02&q=yellow+flowers             &image_type=photo&pretty=true
        return "https://pixabay.com/api/?key=18611589-dbade0af15519d715c91bde02&q=${keyWord.random()}&per_page=100"


    }

    private val keyWord =
        arrayOf("cat", "dog", "car", "beauty", "phone", "flower", "sexy", "animal", "student")*/
}