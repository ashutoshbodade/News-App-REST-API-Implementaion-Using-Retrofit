package com.ashutosh.newsapp.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.ashutosh.newsapp.NewsDetailViewActivity
import com.ashutosh.newsapp.R
import com.ashutosh.newsapp.database.News
import com.ashutosh.newsapp.database.NewsDAO
import com.ashutosh.newsapp.database.NewsDatabase
import com.ashutosh.newsapp.model.Article
import com.ashutosh.newsapp.model.NewsSerializableData
import com.bumptech.glide.Glide
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class NewsAdapter(val itemListData: ArrayList<Article>,val context: Context, val isDataFromLocal:Boolean) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
    var newsDao:NewsDAO? = null
    init {
        newsDao = NewsDatabase.getInstance(context).newsDAO()
    }



    fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
        return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val inflatedView = parent.inflate(R.layout.list_news, false)
        return ViewHolder(inflatedView)
    }

    override fun getItemCount(): Int {
        return itemListData.size
    }

    @DelicateCoroutinesApi
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(itemListData[position])


        if(itemListData[position].urlToImage != null)
            Glide.with(context).load(itemListData[position].urlToImage).centerCrop().into(holder.imgUrl)


        holder.itemView.setOnClickListener {
            val intent = Intent(context, NewsDetailViewActivity::class.java)
            intent.putExtra("NEWS_ARTICLE", NewsSerializableData(itemListData[position].author,itemListData[position].content
                ,itemListData[position].description,itemListData[position].publishedAt,
                itemListData[position].source.id,itemListData[position].source.name,
                itemListData[position].title,itemListData[position].url,itemListData[position].urlToImage))

            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }

        if(!isDataFromLocal)
        {
            holder.imgBookmark.visibility=View.VISIBLE
        }

    holder.imgBookmark.setOnClickListener {
        insert(News(itemListData[position].author,itemListData[position].content
            ,itemListData[position].description,itemListData[position].publishedAt,
            itemListData[position].source.id,itemListData[position].source.name,
            itemListData[position].title,itemListData[position].url,itemListData[position].urlToImage))
        Toast.makeText(context,"Added to bookmark ${itemListData[position].title}",Toast.LENGTH_SHORT).show()
    }



    }

    @DelicateCoroutinesApi
    fun insert(news: News) {
        GlobalScope.launch(Dispatchers.IO) {
            newsDao!!.insert(news)
        }

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {



        var txtTitle = itemView.findViewById<TextView>(R.id.txtTitle)
        var txtPublished = itemView.findViewById<TextView>(R.id.txtPublished)
        var imgUrl = itemView.findViewById<ImageView>(R.id.imgUrl)
        var imgBookmark = itemView.findViewById<ImageView>(R.id.imgBookmark)
        var imgDelete = itemView.findViewById<ImageView>(R.id.imgDelete)

        fun bindItems(dataitem: Article) {

            txtTitle.setText(dataitem.title)
            txtPublished.setText(dataitem.publishedAt)



        }

    }

}