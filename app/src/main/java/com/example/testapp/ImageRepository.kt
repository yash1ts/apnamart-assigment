package com.example.testapp

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ImageRepository {
    companion object {
        private const val DEFAULT_PAGE_SIZE = 20
        private const val BASE_URL = "https://picsum.photos/"

        fun getInstance() = ImageRepository()

        fun getService(): ApiService = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiService::class.java)
    }

    private fun getDefaultPageConfig(): PagingConfig {
        return PagingConfig(pageSize = DEFAULT_PAGE_SIZE, enablePlaceholders = true)
    }

    fun letImagesLiveData(pagingConfig: PagingConfig = getDefaultPageConfig()): LiveData<PagingData<ImageModel>> {
        val service = getService()
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = { Source(service) }
        ).liveData
    }
}