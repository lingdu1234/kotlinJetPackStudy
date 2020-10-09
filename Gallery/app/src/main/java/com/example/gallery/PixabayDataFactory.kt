package com.example.gallery

import android.content.Context
import androidx.paging.DataSource


class PixabayDataFactory(private val context: Context): DataSource.Factory<Int,PhotoItem>() {
    override fun create(): DataSource<Int, PhotoItem> {
        return PixabayDataSource(context)
    }
}