package com.example.newsapplication.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
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
import com.google.android.gms.auth.api.signin.GoogleSignIn
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileFragment : Fragment() {

    private var userId: String? = null

    //On View Creation////////////////////////////////////////////////////////////////////////////
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val acct = GoogleSignIn.getLastSignedInAccount(context)
        if (acct != null) {
            val userId = acct.id
        }
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }////////////////////////////////////////////////////////////////////////////////////////////

    //Upon Creating View////////////////////////////////////////////////////////////////////////////
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Initialize EndPoints and Create List to contain all markers
        val request = ServiceBuilder.buildService(EndPoints::class.java)


        Toast.makeText(activity, "Welcome $userId",
                Toast.LENGTH_SHORT).show()




    }////////////////////////////////////////////////////////////////////////////////////////////

}