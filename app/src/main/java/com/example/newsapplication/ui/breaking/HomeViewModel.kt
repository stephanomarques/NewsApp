package com.example.newsapplication.ui.breaking

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BreakingViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is the Breaking Fragment"
    }
    val text: LiveData<String> = _text
}