package com.ashutosh.newsapp.model

data class NewsDataModel(
    val articles: ArrayList<Article>,
    val status: String,
    val totalResults: Int
)