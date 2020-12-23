package com.example.newsapplication

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {

    private var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Redirect Button to Register Page////////////////////////////////////////////////
        val redirectRegister = findViewById<TextView>(R.id.register_button_login_page)
        redirectRegister.setOnClickListener {
            val intent = Intent(this@MainActivity, RegisterUser::class.java)
            startActivity(intent)
        }//////////////////////////////////////////////////////////////////////////////////


        //Firebase Authentication///////////////
        mAuth = FirebaseAuth.getInstance();
        ////////////////////////////////////////

    }










}