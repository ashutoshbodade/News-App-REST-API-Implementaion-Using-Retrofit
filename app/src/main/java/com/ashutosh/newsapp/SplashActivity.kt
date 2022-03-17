package com.ashutosh.newsapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }
    override fun onStart() {

        super.onStart()
        if( Constant.mAuth.currentUser==null)
        {
            startActivity(Intent(this, LogInActivity::class.java))
            finish()
        }
        else if(Constant.mAuth.currentUser!!.displayName == null)
        {
            startActivity(Intent(this, UpdateUserDetailsActivity::class.java))
            finish()
        }
        else
        {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

    }
}