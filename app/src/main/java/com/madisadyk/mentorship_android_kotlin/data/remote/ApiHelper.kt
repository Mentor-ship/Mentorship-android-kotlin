package com.madisadyk.mentorship_android_kotlin.data.remote

import com.madisadyk.mentorship_android_kotlin.data.model.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header

interface ApiHelper {

    suspend fun getAllVacancies(): Response<List<Vacancy>>

    suspend fun getAllEmployers(): Response<List<Employer>>

    suspend fun getUserInfo(
            @Header("token") token: String
    ): Response<User>

    suspend fun register(
            @Body user: User
    ): Response<LoginResponse>

    suspend fun login(
            @Body request: LoginRequest
    ): Response<LoginResponse>

    suspend fun getCompanyName(
            @Body employerID: EmployerID
    ): Response<CompanyName>

    suspend fun checkMail(
            @Body request: CheckMailRequest
    ): Response<CheckMailResponse>
}