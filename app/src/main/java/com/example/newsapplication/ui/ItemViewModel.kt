package com.example.newsapplication.ui

import androidx.lifecycle.ViewModel
import com.example.newsapplication.entities.News
import com.example.newsapplication.entities.User

class ItemViewModel: ViewModel() {

    private var currentUserId: String = ""

    fun setUserId(userId: String) {
        currentUserId = userId
    }

    fun getCurrentUserId(): String {
        return currentUserId
    }

}