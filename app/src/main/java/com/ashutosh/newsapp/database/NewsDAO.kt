package com.ashutosh.newsapp.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NewsDAO {

    @Insert
    fun insert(news: News)

    @Update
    fun update(news: News)

    @Delete
    fun delete(news: News)

    @Query("delete from news_article_table")
    fun deleteAllNewsArticles()

    @Query("select * from news_article_table")
    fun getAllNewsArticles(): List<News>

}