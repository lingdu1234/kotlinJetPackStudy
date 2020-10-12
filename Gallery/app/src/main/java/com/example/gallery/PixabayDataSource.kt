package com.example.gallery

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.google.gson.Gson

enum class NetworkStatus {
    LOADING,
    FAILED,
    COMPLETED,
}

class PixabayDataSource(private val context: Context) : PageKeyedDataSource<Int, PhotoItem>() {

    /*    companion object {
            const val LOADING = 1
            const val FAILED = 2
            const val COMPLETED = 3
        }*/
    var retry: (() -> Any)? = null
    private val _netWorkStatus = MutableLiveData<NetworkStatus>()
    val networkStatus = _netWorkStatus

    private val queryKey =
        arrayOf("cat", "dog", "car", "beauty", "phone", "flower", "sexy", "animal", "student", "nurse").random()

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, PhotoItem>) {
        _netWorkStatus.postValue(NetworkStatus.LOADING)
        val url = "https://pixabay.com/api/?key=18611589-dbade0af15519d715c91bde02&q=${queryKey}&per_page=66&page=1"
        StringRequest(
            Request.Method.GET,
            url,
            Response.Listener {
                retry = null
                val dataList = Gson().fromJson(it, Pixabay::class.java).hits.toList()
                callback.onResult(dataList, null, 2)
            },
            Response.ErrorListener {
                retry = { loadInitial(params, callback) }
                _netWorkStatus.postValue(NetworkStatus.FAILED)
                Log.d("myTag", it.toString())
            })
            .also { VolleySingleton.getInstance(context).requestQueue.add(it) }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, PhotoItem>) {}

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, PhotoItem>) {
        _netWorkStatus.postValue(NetworkStatus.LOADING)
        val url = "https://pixabay.com/api/?key=18611589-dbade0af15519d715c91bde02&q=${queryKey}&per_page=66&page=${params.key}"
        StringRequest(
            Request.Method.GET,
            url,
            Response.Listener {
                retry = null
                val dataList = Gson().fromJson(it, Pixabay::class.java).hits.toList()
                callback.onResult(dataList, params.key + 1)
            },
            Response.ErrorListener {
                if (it.toString() == "loadAfter:com.android.volley.clientError") {
                    _netWorkStatus.postValue(NetworkStatus.COMPLETED)
                } else {

                    retry = { loadAfter(params, callback) }
                    _netWorkStatus.postValue(NetworkStatus.FAILED)
                }
                Log.d("myTag", it.toString())
            })
            .also { VolleySingleton.getInstance(context).requestQueue.add(it) }
    }
}