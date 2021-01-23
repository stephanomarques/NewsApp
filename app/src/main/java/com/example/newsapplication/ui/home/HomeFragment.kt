package com.example.newsapplication.ui.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment(), NewsAdapter.ClickListener {

    private var thisLayoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<NewsAdapter.NewsHolder>? = null
    private lateinit var sampleNews: List<News>
    private var mAdapter: NewsAdapter? = null

    //On View Creation////////////////////////////////////////////////////////////////////////////
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }////////////////////////////////////////////////////////////////////////////////////////////

    //Upon Creating View////////////////////////////////////////////////////////////////////////////
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Initialize EndPoints and Create List to contain all markers
        val request = ServiceBuilder.buildService(EndPoints::class.java)

        //recycler
        val recyclerView: RecyclerView = view.findViewById(R.id.home_recyclerview)
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager

        //Get health news request and pass to adapter////////////////////////////////////////////
        val call: Call<ResponseModel> = request.getHomeNews()
        call.enqueue(object : Callback<ResponseModel> {
            override fun onResponse(call: Call<ResponseModel>, response: Response<ResponseModel>) {
                if (response.isSuccessful) {
                    val theseNews: List<News>? = response.body()!!.allNews
                    if(theseNews != null){
                        mAdapter = setupAdapter(theseNews)
                        recyclerView.adapter = mAdapter
                    }
                }
            }

            override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                Toast.makeText(activity, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }////////////////////////////////////////////////////////////////////////////////////////////

    //Adapter Setup for Recycler////////////////////////////////////////////////////////////
    fun setupAdapter(news: List<News>): NewsAdapter? {
        mAdapter = context?.let { NewsAdapter(it, news, this) }
        return mAdapter
    }////////////////////////////////////////////////////////////////////////////////////

    //Clicked Item Action (Opens URL of News Article in Browser)//////////////////////////////
    override fun ClickedItem(news: News) {
        val url = news.url
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }/////////////////////////////////////////////////////////////////////////////////////////

}