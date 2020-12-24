package com.example.newsapplication

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {

    //Firebase var
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Redirect Button to Register Page////////////////////////////////////////////////
        val redirectRegister = findViewById<TextView>(R.id.register_button_login_page)
        redirectRegister.setOnClickListener {

            val intent = Intent(this@MainActivity, RegisterUser::class.java)
            startActivity(intent)

        }//////////////////////////////////////////////////////////////////////////////////

        //Firebase Authentication/////////////////////////////////////////////////////////
        auth = Firebase.auth
        /////////////////////////////////////////////////////////////////////////////////
    }

    //Start check for signed in user
    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    fun updateUI(currentUser: FirebaseUser?){

    }
}