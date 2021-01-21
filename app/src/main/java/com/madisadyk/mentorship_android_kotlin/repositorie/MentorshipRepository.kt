package com.madisadyk.mentorship_android_kotlin.repositorie

import com.madisadyk.mentorship_android_kotlin.data.remote.RetrofitInstance
import com.madisadyk.mentorship_android_kotlin.model.CheckMailRequest
import com.madisadyk.mentorship_android_kotlin.model.LoginRequest
import retrofit2.Response

class MentorshipRepository() {

    suspend fun getAllVacancies() =
        RetrofitInstance.api.getAllVacancies()

    suspend fun getAllEmployers() =
        RetrofitInstance.api.getAllEmployers()

    suspend fun getUserInfo(token: String) =
        RetrofitInstance.api.getUserInfo(token)

    suspend fun login(email: String, password: String) =
        RetrofitInstance.api.login(LoginRequest(email, password))

    suspend fun checkMail(email: String) =
        RetrofitInstance.api.checkMail(CheckMailRequest(email))
}