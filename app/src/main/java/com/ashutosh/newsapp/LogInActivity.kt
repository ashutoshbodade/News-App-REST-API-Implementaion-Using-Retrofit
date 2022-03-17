package com.ashutosh.newsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.FirebaseAuth

class LogInActivity : AppCompatActivity() {
    val TAG = "LogInActivity"
    lateinit var btnLogIn:Button

    private val signInLauncher = registerForActivityResult(
        FirebaseAuthUIActivityResultContract()
    ) { res ->
        this.onSignInResult(res)
    }

    private val providers = arrayListOf(
        AuthUI.IdpConfig.EmailBuilder().build(),
        AuthUI.IdpConfig.PhoneBuilder().build(),
        AuthUI.IdpConfig.GoogleBuilder().build()
    )

    private val signInIntent = AuthUI.getInstance()
        .createSignInIntentBuilder()
        .setAvailableProviders(providers)
        .setLogo(R.drawable.newspaper)
        .build()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)
        btnLogIn = findViewById(R.id.btnLogIn)



        btnLogIn.setOnClickListener {
            signInLauncher.launch(signInIntent)
        }


    }

    private fun onSignInResult(result: FirebaseAuthUIAuthenticationResult) {
        val response = result.idpResponse
        if (result.resultCode == RESULT_OK) {
          
           if(Constant.mAuth.currentUser!!.displayName == null)
           {
               finish()
               startActivity(Intent(this, UpdateUserDetailsActivity::class.java))


           }
            else
           {
               finish()
               startActivity(Intent(this, MainActivity::class.java))

           }


        }
        else
        {
            Log.e(TAG, "onSignInResult: $response", )
        }
    }

}