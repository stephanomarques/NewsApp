package com.example.newsapplication.ui.world

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WorldViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is the WORLD news Fragment"
    }
    val text: LiveData<String> = _text
}