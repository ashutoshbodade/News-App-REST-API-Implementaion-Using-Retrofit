package com.ashutosh.newsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.google.firebase.auth.ktx.userProfileChangeRequest

class UpdateUserDetailsActivity : AppCompatActivity() {
    val TAG="UpdateUserDetails"
    lateinit var txtName:EditText
    lateinit var btnSave:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_user_details)
        txtName= findViewById(R.id.txtName)
        btnSave= findViewById(R.id.btnSave)

        btnSave.setOnClickListener {
            if(txtName.text.toString() != ""){
                val profileUpdates = userProfileChangeRequest {
                    displayName = "Jane Q. User"
                }

                Constant.mAuth.currentUser!!.updateProfile(profileUpdates)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful)
                        {
                            startActivity(Intent(this, MainActivity::class.java))
                            finish()
                        }
                        else
                        {
                            Log.e(TAG, "Failed to update used display name ", )
                        }
                    }
            }
            else txtName.setError("Please Enter Name")

        }
    }
}