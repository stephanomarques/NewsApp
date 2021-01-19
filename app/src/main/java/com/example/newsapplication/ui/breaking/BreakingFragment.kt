package com.example.newsapplication.ui.breaking

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapplication.R
import com.example.newsapplication.adapter.NewsAdapter
import com.example.newsapplication.api.EndPoints
import com.example.newsapplication.api.ServiceBuilder
import com.example.newsapplication.entities.News


class BreakingFragment : Fragment() {

    private lateinit var breakingViewModel: BreakingViewModel
    private var thisLayoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<NewsAdapter.NewsHolder>? = null
    private lateinit var sampleNews: List<News>
    private var mAdapter: NewsAdapter? = null


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_breaking, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Initialize EndPoints and Create List to contain all markers
        val request = ServiceBuilder.buildService(EndPoints::class.java)

        // recycler view
        //val recyclerView = view.findViewById<RecyclerView>(R.id.breaking_recyclerview)

        //recycler
        val recyclerView: RecyclerView = view.findViewById(R.id.breaking_recyclerview)
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager

        val sampleNews = listOf<News>(
                News("wewe", "wew", "wewe", "wewe", "wew", "wew"),
                News("qqq", "wew", "wewe", "wewe", "wew", "wew"),
                News("asasde", "wew", "wewe", "wewe", "wew", "wew"),
                News("stwewe", "wew", "wewe", "wewe", "wew", "wew"),
                News("wqwea", "wew", "wewe", "wewe", "wew", "wew"),
                News("xzvswe", "wew", "wewe", "wewe", "wew", "wew"),
                News("aweqe", "wew", "wewe", "wewe", "wew", "wew"),
                News("weczsae", "wew", "wewe", "wewe", "wew", "wew"),
                News("weryhnwe", "wew", "wewe", "wewe", "wew", "wew"),
                News("qwqrtwe", "wew", "wewe", "wewe", "wew", "wew")
        )

        // define an adapter
        mAdapter = context?.let { NewsAdapter(it, sampleNews) }
        recyclerView.adapter = mAdapter


        /*val thisAdapter = context?.let { NewsAdapter(it, sampleNews) }
        thisLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = thisLayoutManager
        recyclerView.adapter = thisAdapter*/

        /*
        val call: Call<ResponseModel> = request.getTopNews()
        call.enqueue(object : Callback<ResponseModel> {
            override fun onResponse(call: Call<ResponseModel>, response: Response<ResponseModel>) {
                if (response.isSuccessful) {

                    val theseNews: List<News>? = response.body()!!.allNews

                    if(theseNews != null){
                        mRecyclerView.adapter = NewsAdapter(theseNews)
                    }

                    Toast.makeText(activity, "$theseNews", Toast.LENGTH_SHORT).show()


                    //val thisNewsAdapter: NewsAdapter? = theseNews?.let { NewsAdapter(it) }

                    //if (theseNews != null) {
                    //    mRecyclerView.adapter = thisNewsAdapter
                    //}
                }
            }

            override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                Toast.makeText(activity, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })*/
    }

}