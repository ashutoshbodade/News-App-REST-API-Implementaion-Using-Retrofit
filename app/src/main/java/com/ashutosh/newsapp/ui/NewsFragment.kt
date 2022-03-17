package com.ashutosh.newsapp.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ashutosh.newsapp.Constant
import com.ashutosh.newsapp.R
import com.ashutosh.newsapp.adapter.NewsAdapter
import com.ashutosh.newsapp.api.RetrofitBuilder
import com.ashutosh.newsapp.model.Article
import kotlinx.coroutines.*
import java.lang.Error


class NewsFragment : Fragment() {


    private lateinit var rvNews: RecyclerView
    private lateinit var rvSearchNews: RecyclerView

    private lateinit var listNews: ArrayList<Article>
    private lateinit var listSearchNews: ArrayList<Article>
    private lateinit var newsAdapter: NewsAdapter
    private lateinit var newsSearchAdapter: NewsAdapter
    private lateinit var progress: ProgressBar
    private lateinit var txtDisplayName: TextView
    lateinit var searchview: SearchView

    private var page = 1
    val scrollDirectionDown = 1
    var currentListSize = 0

    val TAG = "NewsFragment"

    @SuppressLint("SetTextI18n")
    @DelicateCoroutinesApi
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_news, container, false)

        rvNews = root.findViewById(R.id.rvNews)
        rvSearchNews = root.findViewById(R.id.rvSearchNews)
        progress = root.findViewById(R.id.progress)
        txtDisplayName = root.findViewById(R.id.txtDisplayName)
        searchview = root.findViewById(R.id.searchview)
        listNews= ArrayList()
        listSearchNews= ArrayList()

        txtDisplayName.setText("Welcome: "+Constant.mAuth.currentUser!!.displayName)

        rvNews.layoutManager = LinearLayoutManager(activity)
        newsAdapter=NewsAdapter(listNews,requireActivity(),false)
        rvNews.adapter = newsAdapter


        rvSearchNews.layoutManager = LinearLayoutManager(activity)
        newsSearchAdapter=NewsAdapter(listSearchNews,requireActivity(),false)
        rvSearchNews.adapter = newsSearchAdapter

        getNews(page)


        searchview.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                search(query!!)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {

                if(newText == ""){
                    rvNews.visibility=View.VISIBLE
                    rvSearchNews.visibility=View.GONE
                }
                else
                {
                    rvNews.visibility=View.GONE
                    rvSearchNews.visibility=View.VISIBLE
                }


                return true
            }

        })


        rvNews.addOnScrollListener(
            object : RecyclerView.OnScrollListener() {

                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)

                    if (!recyclerView.canScrollVertically(scrollDirectionDown)
                        && newState == RecyclerView.SCROLL_STATE_IDLE) {
                        val listSizeAfterLoading = recyclerView.layoutManager!!.itemCount
                        if (currentListSize != listSizeAfterLoading) {
                            progress.visibility = View.VISIBLE
                            currentListSize = listSizeAfterLoading
                            getNews(page)
                            }

                        }

                    }
                })

        return root
    }


    @SuppressLint("NotifyDataSetChanged")
    @DelicateCoroutinesApi
    private fun search(query:String){
        GlobalScope.launch(Dispatchers.IO) {
            val newsResponse = RetrofitBuilder.apiService.searchEverything(query)
            withContext(Dispatchers.Main){
                try {
                    if (newsResponse.isSuccessful)
                    {
                        listSearchNews.clear()
                        listSearchNews.addAll(newsResponse.body()!!.articles)
                        Log.d(TAG, "onCreate: $page ${newsResponse.body()}")
                        newsSearchAdapter.notifyDataSetChanged()

                    }
                }
                catch (err: Error){
                    Log.e(TAG, "onCreate: ",err )
                }
            }
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    @DelicateCoroutinesApi
    private fun getNews(pageNo:Int){
        GlobalScope.launch(Dispatchers.IO) {
            val newsResponse = RetrofitBuilder.apiService.getTopHeadlines(pageNo)
            withContext(Dispatchers.Main){
                try {
                    if (newsResponse.isSuccessful)
                    {
                        listNews.addAll(newsResponse.body()!!.articles)
                        Log.d(TAG, "onCreate: $page ${newsResponse.body()}")
                        newsAdapter.notifyDataSetChanged()
                        progress.visibility=View.GONE
                        page++
                    }
                }
                catch (err: Error){
                    Log.e(TAG, "onCreate: ",err )
                }
            }
        }

    }

}