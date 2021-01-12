package com.example.newsapplication.ui.tech

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.newsapplication.R

class TechFragment : Fragment() {

    private lateinit var techViewModel: TechViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        techViewModel =
                ViewModelProvider(this).get(TechViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_tech, container, false)
        val textView: TextView = root.findViewById(R.id.text_tech)
        techViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}