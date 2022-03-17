package com.ashutosh.newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.ashutosh.newsapp.model.NewsSerializableData
import com.bumptech.glide.Glide
import android.content.Intent
import android.net.Uri


class NewsDetailViewActivity : AppCompatActivity() {
    val TAG = "NewsDetailActivity"
    lateinit var txtTitle:TextView
    lateinit var imgUrl:ImageView
    lateinit var txtDesc:TextView
    lateinit var txtContent:TextView
    lateinit var txtAuthor:TextView
    lateinit var txtSource:TextView
    lateinit var btnReadDetailNews:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_detail_view)

        txtTitle=findViewById(R.id.txtTitle)
        imgUrl=findViewById(R.id.imgUrl)
        txtDesc=findViewById(R.id.txtDesc)
        txtContent=findViewById(R.id.txtContent)
        txtAuthor=findViewById(R.id.txtAuthor)
        txtSource=findViewById(R.id.txtSource)
        btnReadDetailNews=findViewById(R.id.btnReadDetailNews)

        val news = intent.extras!!.getSerializable("NEWS_ARTICLE")  as NewsSerializableData
        Log.d(TAG, "onCreate: $news")

        txtTitle.setText(news.title)
        txtDesc.setText(news.description)


        if(news.author!=null)
            txtAuthor.setText("Author: "+news.author)
        if(news.sourcename!=null)
            txtSource.setText("Source: "+news.sourcename)
        if(news.content!=null)
            txtContent.setText(news.content)
        if (news.urlToImage != null)
            Glide.with(this).load(news.urlToImage).into(imgUrl)

        btnReadDetailNews.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(news.url)
            startActivity(intent)
        }

    }
}