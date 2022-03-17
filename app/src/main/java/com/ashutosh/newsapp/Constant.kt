package com.ashutosh.newsapp

import com.google.firebase.auth.FirebaseAuth

class Constant {

    companion object {

        val mAuth: FirebaseAuth = FirebaseAuth.getInstance()
        const val BASE_URL = "https://newsapi.org"
        const val API_KEY = "8b01d016150d4e59b04ef52f9b62279e"
    }

}