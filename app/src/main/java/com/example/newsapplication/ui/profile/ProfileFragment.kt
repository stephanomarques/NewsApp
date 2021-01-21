package com.example.newsapplication.ui.profile

import android.os.Bundle
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
import com.example.newsapplication.entities.User
import com.example.newsapplication.ui.ItemViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileFragment : Fragment() {

    private val viewModel: ItemViewModel by activityViewModels()
    private var thisUser: String = ""
    private var updateButton: Button = TODO()

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

        //Initialize EndPoints and Create List to contain all markers
        val request = ServiceBuilder.buildService(EndPoints::class.java)

        val checkboxHealth: CheckBox = view.findViewById(R.id.checkbox_health)
        val checkboxBusiness: CheckBox = view.findViewById(R.id.checkbox_business)
        val checkboxSports: CheckBox = view.findViewById(R.id.checkbox_sport)
        val checkboxScience: CheckBox = view.findViewById(R.id.checkbox_science)
        val checkboxTech: CheckBox = view.findViewById(R.id.checkbox_tech)
        val checkboxEntertainment: CheckBox = view.findViewById(R.id.checkbox_entertainment)

        if(checkboxBusiness.isChecked){
            Toast.makeText(activity, "Ticked",
                Toast.LENGTH_SHORT).show()
        }

        updateButton = view.findViewById(R.id.news_type_btn)
        updateButton.setOnClickListener {

        }


    }////////////////////////////////////////////////////////////////////////////////////////////

}