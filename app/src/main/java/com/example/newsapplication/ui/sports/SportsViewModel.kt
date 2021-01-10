package com.example.newsapplication.ui.sports

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SportsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is the Sports Fragment"
    }
    val text: LiveData<String> = _text
}