package com.example.newsapplication.entities

data class User(
        var userId: String? = "",
        var email: String? = "",
)

data class Types(
        var Business: Boolean = false,
        var Health: Boolean = false,
        var Science: Boolean = false,
        var Sports: Boolean = false,
        var Tech: Boolean = false,
)