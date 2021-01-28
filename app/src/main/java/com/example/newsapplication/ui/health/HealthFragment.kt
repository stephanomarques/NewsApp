package com.example.newsapplication.ui.health

import android.content.ContentValues.TAG
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapplication.R
import com.example.newsapplication.adapter.NewsAdapter
import com.example.newsapplication.api.EndPoints
import com.example.newsapplication.api.NotificationsRetroFit
import com.example.newsapplication.api.ServiceBuilder
import com.example.newsapplication.entities.News
import com.example.newsapplication.entities.NotificationData
import com.example.newsapplication.entities.PushNotification
import com.example.newsapplication.entities.ResponseModel
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

const val TOPIC = "/topics/health"

class HealthFragment : Fragment(), NewsAdapter.ClickListener {

    private var thisLayoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<NewsAdapter.NewsHolder>? = null
    private lateinit var sampleNews: List<News>
    private var mAdapter: NewsAdapter? = null
    val TAG = "Health Fragment"

    //On View Creation////////////////////////////////////////////////////////////////////////////
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_health, container, false)
    }////////////////////////////////////////////////////////////////////////////////////////////

    //Upon Creating View////////////////////////////////////////////////////////////////////////////
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Initialize EndPoints and Create List to contain all markers
        val request = ServiceBuilder.buildService(EndPoints::class.java)

        //recycler
        val recyclerView: RecyclerView = view.findViewById(R.id.health_recyclerview)
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager

        //Get health news request and pass to adapter////////////////////////////////////////////
        val call: Call<ResponseModel> = request.getHealthNews()
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

        val title = news.title
        val message = news.description
        val image = news.image

        if(title.isNotEmpty() && message.isNotEmpty()){
            PushNotification(
                    NotificationData(title, message, image),
                    TOPIC
            ).also{
                    sendNotification(it)
            }
        }

        //val url = news.url
        //val intent = Intent(Intent.ACTION_VIEW)
        //intent.data = Uri.parse(url)
        //startActivity(intent)
    }/////////////////////////////////////////////////////////////////////////////////////////

    private fun sendNotification(notification: PushNotification) = CoroutineScope(Dispatchers.IO).launch {
        try{
            val response = NotificationsRetroFit.api.postNotification(notification)
            if(response.isSuccessful){
                Log.d(TAG, "Response: $response" )
            }else{
                Log.e(TAG, response.errorBody().toString())
            }
        }catch (e: Exception) {
            Log.e(TAG, e.toString())
        }
    }

}