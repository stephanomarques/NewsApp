package com.example.newsapplication.ui.science

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ScienceViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is the Science Fragment"
    }
    val text: LiveData<String> = _text
}