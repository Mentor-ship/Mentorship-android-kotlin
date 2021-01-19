package com.madisadyk.mentorship_android_kotlin.repositorie

import com.madisadyk.mentorship_android_kotlin.data.remote.RetrofitInstance
import retrofit2.Response

class MentorshipRepository() {

    suspend fun getAllVacancies() =
        RetrofitInstance.api.getAllVacancies()

    suspend fun getAllEmployers() =
        RetrofitInstance.api.getAllEmployers()

    suspend fun getUserInfo(token: String) =
        RetrofitInstance.api.getUserInfo(token)
}