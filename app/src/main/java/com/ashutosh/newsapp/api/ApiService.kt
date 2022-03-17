package com.ashutosh.newsapp.api

import com.ashutosh.newsapp.Constant
import com.ashutosh.newsapp.model.NewsDataModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiService {

    @GET("/v2/top-headlines")
    suspend fun getTopHeadlines(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int = 20,
        @Query("country") country:String = "in",
        @Header("Authorization") token: String = Constant.API_KEY
    ): Response<NewsDataModel>

    @GET("/v2/everything")
    suspend fun searchEverything(
        @Query("q") q: String,
        @Query("sortBy") sortBy:String="popularity",
        @Header("Authorization") token: String = Constant.API_KEY
    ): Response<NewsDataModel>
}