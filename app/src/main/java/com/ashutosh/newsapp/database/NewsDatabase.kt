package com.ashutosh.newsapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(version = 1, entities = [News::class], exportSchema = false)
abstract class NewsDatabase: RoomDatabase() {
    abstract fun newsDAO():NewsDAO

    companion object
    {
        private var instance:NewsDatabase?=null

        fun getInstance(context: Context):NewsDatabase{
            if (instance==null)
                instance= Room.databaseBuilder<NewsDatabase>(context, NewsDatabase::class.java,"NewsDB2").build()
            return instance!!

        }
    }
}
