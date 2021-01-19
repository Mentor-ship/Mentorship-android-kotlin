package com.madisadyk.mentorship_android_kotlin.model

data class User(
    val userId: String,
    val userName: String,
    val email: String,
    val phoneNumber: String,
    val password: String,
    val firstName: String,
    val lastName: String,
    val country: String,
    val city: String
)