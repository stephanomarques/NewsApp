package com.example.newsapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.newsapplication.entities.Types
import com.example.newsapplication.entities.User
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignIn.getClient
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    //Firebase var
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference

    private lateinit var googleSignInClient: GoogleSignInClient

    //Vars to hold activity layout elements
    private lateinit var edit_text_email: EditText
    private lateinit var edit_text_password: EditText

    companion object{
        private const val GOOGLE_SIGN_IN = 120
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Firebase Authentication/////////////////////////////////////////////////////////
        auth = Firebase.auth
        /////////////////////////////////////////////////////////////////////////////////

        val loginButton = findViewById<SignInButton>(R.id.google_button)
        loginButton.setOnClickListener {
            signIn()
        }

        // Configure Google Sign In ////////////////////////////////////////////////////////////
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()
        googleSignInClient = getClient(this, gso)
        ////////////////////////////////////////////////////////////////////////////////////////
    }

    //Start check for signed in user////////////////////////////////////////////////////////
    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }////////////////////////////////////////////////////////////////////////////////////

    //SignIn Function Called When Google Login Button Pressed
    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, GOOGLE_SIGN_IN)
    }////////////////////////////////////////////////////////

    //Sign In Activity Result Asked by SignIn Function//////////////////////////////////////////////
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from googleSignInClient.signInIntent
        if (requestCode == GOOGLE_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            val exception = task.exception

            if(task.isSuccessful){
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
                Log.d("SignInActivity", "firebaseAuthWithGoogle:" + account.id)
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w("SignInActivity", "Google sign in failed", e)
            }
            }else{
                //if task not successful
                Log.w("SignInActivity", exception.toString())
                Toast.makeText(this, "Google Sign in Failed!",
                        Toast.LENGTH_SHORT).show()
            }
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////

    //Firebase Authentication when Google Sign in is clicked////////////////////////////
    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        //Returns True if User is New and False if user exists already
                        val isNew: Boolean? = task.result?.additionalUserInfo?.isNewUser

                        //Se utilizador for novo, criar na base de dados entrada para ele
                        if(isNew == true){
                            // Criar child no database com uid do novo user
                            val currentUser = auth.currentUser
                            writeNewUser(currentUser)
                        }

                        // Sign in success, update UI with the signed-in user's information
                        Log.d("SignInActivity", "signInWithCredential:success")
                        Log.d("isNewUser:", "$isNew")

                        //val user = auth.currentUser
                        val intent = Intent(this, HomePageActivity::class.java)
                        startActivity(intent)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("SignInActivity", "signInWithCredential:failure", task.exception)
                    }
                }
    }////////////////////////////////////////////////////////////////////////////////////

    //Update UI runs every startup to continue the past session (if not signed out)
    fun updateUI(currentUser: FirebaseUser?) {
        if(currentUser != null){
            val currentUserUid: String? = currentUser.uid
            val intent = Intent(this, HomePageActivity::class.java)
            intent.putExtra("loggedInUserUID", currentUserUid)
            startActivity(intent)
        }
    }////////////////////////////////////////////////////////////////////////////////////

    //New User Inserted in RTDB////////////////////////////////////////////////////////////////////
    fun writeNewUser(currentUser: FirebaseUser?) {
        val user = User(currentUser?.uid, currentUser?.email)
        val database = Firebase.database

        //Default Types to be Inserted
        val types: Types = Types(Business = false, Health = false, Science = false, Sports = false, Tech = false)

        val reference = database.getReference("/Users")

        //Insert uid as Users child and types and uid children
        reference.child(currentUser!!.uid).setValue(types)
    }/////////////////////////////////////////////////////////////////////////////////////////////

}