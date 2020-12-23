package com.example.newsapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Button
import android.widget.EditText

class RegisterUser : AppCompatActivity() {

    //Vars to hold activity layout elements
    private lateinit var edit_text_name: EditText
    private lateinit var edit_text_email: EditText
    private lateinit var edit_text_password: EditText
    private lateinit var button_register: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_user)

        //Vars to hold data written in EditTexts
        edit_text_name = findViewById(R.id.edit_text_name)
        edit_text_email = findViewById(R.id.edit_text_email)
        edit_text_password = findViewById(R.id.edit_text_password)
        button_register = findViewById(R.id.button_register)

        //Register Button Click Listener////////////////////////////////////////
        button_register.setOnClickListener {

            if(TextUtils.isEmpty(edit_text_name.text)){
                edit_text_email.error = "Email Required"
            }

            if(TextUtils.isEmpty(edit_text_email.text)){
                edit_text_email.error = "Email Required"
            }

            if(TextUtils.isEmpty(edit_text_password.text)){
                edit_text_password.error = "Password Required"
            }

            if(!Patterns.EMAIL_ADDRESS.matcher(edit_text_email.text).matches()){
                edit_text_email.error = "Needs to be Email!"
            }


        }////////////////////////////////////////////////////////////////////////

        



    }
}