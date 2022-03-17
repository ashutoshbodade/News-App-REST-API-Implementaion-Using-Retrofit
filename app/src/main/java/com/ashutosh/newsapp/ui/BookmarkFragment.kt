package com.ashutosh.newsapp.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ashutosh.newsapp.R
import com.ashutosh.newsapp.adapter.BookmarkNewsAdapter
import com.ashutosh.newsapp.adapter.NewsAdapter
import com.ashutosh.newsapp.database.News
import com.ashutosh.newsapp.database.NewsDatabase
import com.ashutosh.newsapp.model.Article
import kotlinx.coroutines.*


class BookmarkFragment : Fragment() {

    private lateinit var rvNews: RecyclerView

    private lateinit var listNews: ArrayList<News>
    private lateinit var bookmarkNewsAdapter: BookmarkNewsAdapter


    @DelicateCoroutinesApi
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
        ): View? {

        val root = inflater.inflate(R.layout.fragment_bookmark,container,false)

        rvNews = root.findViewById(R.id.rvNews)

        listNews= ArrayList()

        rvNews.layoutManager = LinearLayoutManager(activity)
        bookmarkNewsAdapter= BookmarkNewsAdapter(listNews,requireActivity(),true)
        rvNews.adapter = bookmarkNewsAdapter

        getNewsfromroomDB()

        return root
    }

    @SuppressLint("NotifyDataSetChanged")
    @DelicateCoroutinesApi
    private fun getNewsfromroomDB(){
        GlobalScope.launch(Dispatchers.IO) {
            val newsDao = NewsDatabase.getInstance(requireActivity()).newsDAO()
            listNews.addAll(newsDao.getAllNewsArticles())

            withContext(Dispatchers.Main){
                bookmarkNewsAdapter.notifyDataSetChanged()
            }


            Log.d("TAG", "getNewsfromroomDB: ${newsDao.getAllNewsArticles().toString()}")
        }
    }

}