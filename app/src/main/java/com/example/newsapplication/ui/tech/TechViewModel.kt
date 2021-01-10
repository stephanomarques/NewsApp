package com.example.newsapplication.ui.tech

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TechViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is the Technology Fragment"
    }
    val text: LiveData<String> = _text
}