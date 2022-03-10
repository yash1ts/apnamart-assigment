package com.example.testapp

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("v2/list")
    suspend fun getImages(@Query("page") page: Int, @Query("limit") size: Int): List<ImageModel>

}