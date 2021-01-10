package com.example.newsapplication.ui.sports

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.newsapplication.R

class SportsFragment : Fragment() {

    private lateinit var sportsViewModel: SportsViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        sportsViewModel =
                ViewModelProvider(this).get(SportsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_sports, container, false)
        val textView: TextView = root.findViewById(R.id.text_sports)
        sportsViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}