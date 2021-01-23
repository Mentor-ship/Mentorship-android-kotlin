package com.madisadyk.mentorship_android_kotlin.data.repository

import com.madisadyk.mentorship_android_kotlin.data.model.CheckMailRequest
import com.madisadyk.mentorship_android_kotlin.data.model.LoginRequest
import com.madisadyk.mentorship_android_kotlin.data.model.User
import com.madisadyk.mentorship_android_kotlin.data.remote.ApiHelper
import javax.inject.Inject

class MentorshipRepository @Inject constructor(
    private val apiHelper: ApiHelper
) {

    suspend fun getAllVacancies() =
            apiHelper.getAllVacancies()

    suspend fun getAllEmployers() =
            apiHelper.getAllEmployers()

    suspend fun getUserInfo(token: String) =
            apiHelper.getUserInfo(token)

    suspend fun login(email: String, password: String) =
            apiHelper.login(LoginRequest(email, password))

    suspend fun checkMail(email: String) =
            apiHelper.checkMail(CheckMailRequest(email))

    suspend fun register(user: User) =
            apiHelper.register(user)
}