package com.example.newsapplication.ui.science

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.newsapplication.R

class ScienceFragment : Fragment() {

    private lateinit var scienceViewModel: ScienceViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        scienceViewModel =
                ViewModelProvider(this).get(ScienceViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_science, container, false)
        val textView: TextView = root.findViewById(R.id.text_science)
        scienceViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}