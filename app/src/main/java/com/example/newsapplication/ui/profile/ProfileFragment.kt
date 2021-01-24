package com.example.newsapplication.ui.profile

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapplication.R
import com.example.newsapplication.adapter.NewsAdapter
import com.example.newsapplication.api.EndPoints
import com.example.newsapplication.api.ServiceBuilder
import com.example.newsapplication.entities.News
import com.example.newsapplication.entities.ResponseModel
import com.example.newsapplication.entities.Types
import com.example.newsapplication.entities.User
import com.example.newsapplication.ui.ItemViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileFragment : Fragment() {

    private val viewModel: ItemViewModel by activityViewModels()
    private var thisUser: String = ""

    //On View Creation////////////////////////////////////////////////////////////////////////////
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //Fetches UID of Logged User from ItemViewModel
        thisUser = viewModel.getCurrentUserId()

        return inflater.inflate(R.layout.fragment_profile, container, false)
    }////////////////////////////////////////////////////////////////////////////////////////////

    //Upon Creating View////////////////////////////////////////////////////////////////////////////
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val database = FirebaseDatabase.getInstance().reference
        //user id --> thisUser
        
        //Initialize EndPoints and Create List to contain all markers
        val request = ServiceBuilder.buildService(EndPoints::class.java)

        val checkboxHealth: CheckBox = view.findViewById(R.id.checkbox_health)
        val checkboxBusiness: CheckBox = view.findViewById(R.id.checkbox_business)
        val checkboxSports: CheckBox = view.findViewById(R.id.checkbox_sport)
        val checkboxScience: CheckBox = view.findViewById(R.id.checkbox_science)
        val checkboxTech: CheckBox = view.findViewById(R.id.checkbox_tech)
        val checkboxEntertainment: CheckBox = view.findViewById(R.id.checkbox_entertainment)

        //Query Para Saber que tipos de notícia o utilizador já está subscrito
        val getdata = object : ValueEventListener{
            override fun onDataChange(p0: DataSnapshot) {

                for (i in p0.children) {
                    val businessBoolean: String = i.child("$thisUser/business").value.toString()
                    val sportBoolean: String = i.child("$thisUser/sports").value.toString()
                    val healthBoolean: String = i.child("$thisUser/health").value.toString()
                    val entertainmentBoolean: String = i.child("$thisUser/entertainment").value.toString()
                    val technologyBoolean: String = i.child("$thisUser/tech").value.toString()
                    val scienceBoolean: String = i.child("$thisUser/science").value.toString()

                    //Tick the Checkboxes that are favourites of that user in the database
                    if (businessBoolean == "true") {
                        checkboxBusiness.isChecked = true
                    }
                    if (sportBoolean == "true") {
                        checkboxSports.isChecked = true
                    }
                    if (healthBoolean == "true") {
                        checkboxHealth.isChecked = true
                    }
                    if (entertainmentBoolean == "true") {
                        checkboxEntertainment.isChecked = true
                    }
                    if (technologyBoolean == "true") {
                        checkboxTech.isChecked = true
                    }
                    if (scienceBoolean == "true") {
                        checkboxScience.isChecked = true
                    }
                    ///////////////////////////////////////////////////////////////////////////////
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("OnCancelledCheckbox", "Database Error")
            }

        }
        database.addValueEventListener(getdata)
        database.addListenerForSingleValueEvent(getdata)
        /////////////////////////////////////////////////////////////////////////////////////////////



        // Update Button Listener ////////////////////////////////////////////////////////////////
        val updateButton: Button = view.findViewById(R.id.news_type_btn)
        updateButton.setOnClickListener {

            //Types Updated
            val types = Types(
                    Business = checkboxBusiness.isChecked,
                    Health = checkboxHealth.isChecked,
                    Science = checkboxScience.isChecked,
                    Sports = checkboxSports.isChecked,
                    Tech = checkboxTech.isChecked,
                    Entertainment = checkboxEntertainment.isChecked)

            if(checkboxBusiness.isChecked){
                subscribeTopic("business")
            }

            if(checkboxHealth.isChecked){
                subscribeTopic("health")
            }

            if(checkboxScience.isChecked){
                subscribeTopic("science")
            }

            if(checkboxSports.isChecked){
                subscribeTopic("sports")
            }

            if(checkboxTech.isChecked){
                subscribeTopic("tech")
            }

            if(checkboxEntertainment.isChecked){
                subscribeTopic("entertainment")
            }

            //Insert uid as Users child and types and uid children
            database.child("/Users/$thisUser").setValue(types)


            //Toast.makeText(activity, "Updated!", Toast.LENGTH_SHORT).show()
        }/////////////////////////////////////////////////////////////////////////////////////////


    }////////////////////////////////////////////////////////////////////////////////////////////


    private fun subscribeTopic(TOPIC: String) {
        // [START subscribe_topics]
        FirebaseMessaging.getInstance().subscribeToTopic(TOPIC)
                .addOnCompleteListener { task ->
                    var msg = getString(R.string.message_subscribed)
                    if (!task.isSuccessful) {
                        msg = getString(R.string.message_subscribe_failed)
                    }
                    Log.v("Subscribed: ", msg)
                    Toast.makeText(activity, "$msg", Toast.LENGTH_SHORT).show()
                }
        // [END subscribe_topics]
    }


}