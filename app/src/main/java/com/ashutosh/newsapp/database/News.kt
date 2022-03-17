package com.ashutosh.newsapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "news_article_table")
data class News(
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    val sourceid: String?,
    val sourcename: String?,
    val title: String?,
    val url: String?,
    val urlToImage: String?,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
)