package com.example.gallery

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource


class PixabayDataSourceFactory(private val context: Context) : DataSource.Factory<Int, PhotoItem>() {
    private var _pixabayDataSource: MutableLiveData<PixabayDataSource> = MutableLiveData<PixabayDataSource>()
    val pixabayDataSource: LiveData<PixabayDataSource> = _pixabayDataSource
    override fun create(): DataSource<Int, PhotoItem> {
        return PixabayDataSource(context).also {
            _pixabayDataSource.postValue(it) }
    }
}