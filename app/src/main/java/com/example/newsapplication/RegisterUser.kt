package com.example.newsapplication

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.newsapplication.entities.User
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase


class RegisterUser : AppCompatActivity(){

    private val TAG = "RegisterUserActivity"

    //Vars to hold activity layout elements
    private lateinit var edit_text_name: EditText
    private lateinit var edit_text_email: EditText
    private lateinit var edit_text_password: EditText
    private lateinit var button_register: Button

    //Firebase var
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_user)

        //Firebase Authentication///////////////
        auth = Firebase.auth
        ////////////////////////////////////////

        //Vars to hold data written in EditTexts
        edit_text_name = findViewById(R.id.edit_text_name)
        edit_text_email = findViewById(R.id.edit_text_email)
        edit_text_password = findViewById(R.id.edit_text_password)
        button_register = findViewById(R.id.button_register)

        //Register Button Click Listener////////////////////////////////////////
        button_register.setOnClickListener {
            signUpUser()
        }////////////////////////////////////////////////////////////////////////
    }

    //Function To Sign Up User////////////////////////////////////////////////////////////////////////
    private fun signUpUser(){
        if (TextUtils.isEmpty(edit_text_name.text)) {
            edit_text_email.error = "Email Required"
        }

        if (TextUtils.isEmpty(edit_text_email.text)) {
            edit_text_email.error = "Email Required"
        }

        if (TextUtils.isEmpty(edit_text_password.text)) {
            edit_text_password.error = "Password Required"
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(edit_text_email.text).matches()) {
            edit_text_email.error = "Needs to be Email!"
        }

        //Firebase Authentication///////////////////////////////////////////////////////////////////////////
        auth.createUserWithEmailAndPassword(edit_text_email.text.toString(), edit_text_password.toString())
                .addOnCompleteListener(this, OnCompleteListener<AuthResult?> { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "createUserWithEmail:success")

                        //Declare inserted register params as a variable for a User data class
                        val u = User(edit_text_name.text.toString(), edit_text_email.text.toString(), edit_text_password.text.toString())
                        var thisUser = auth.currentUser?.uid //store the UID generated in auth for real-time-database child uid insertion

                        // Write a message to the database
                        val database = FirebaseDatabase.getInstance()
                        val myRef = database.getReference("Users/")

                        if (thisUser != null) {
                            FirebaseDatabase.getInstance().getReference("Users").child(thisUser).setValue(u).addOnCompleteListener(OnCompleteListener {
                                if(task.isSuccessful){
                                    Log.d(TAG, "UserRealTimeDatabase:success")
                                }else{
                                    Log.d(TAG, "UserRealTimeDatabase:failure")
                                }
                            })
                        }

                        val intent = Intent(this@RegisterUser, MainActivity::class.java)
                        startActivity(intent)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.d(TAG, "createUserWithEmail:failure")
                        Toast.makeText(this@RegisterUser, "Authentication failed.",
                                Toast.LENGTH_SHORT).show()
                        updateUI(null)
                    }
                })


    }///////////////////////////////////////////////////////////////////////////////////////////////////////

    private fun updateUI(currentUser: FirebaseUser?){

    }

}