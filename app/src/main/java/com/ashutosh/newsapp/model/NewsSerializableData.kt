package com.ashutosh.newsapp.model

import java.io.Serializable

data class NewsSerializableData(
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    val sourceid: String?,
    val sourcename: String?,
    val title: String?,
    val url: String?,
    val urlToImage: String?,
  val id: Int = 0
): Serializable