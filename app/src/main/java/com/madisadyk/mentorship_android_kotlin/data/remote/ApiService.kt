package com.madisadyk.mentorship_android_kotlin.data.remote

import com.madisadyk.mentorship_android_kotlin.data.model.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {

    @GET("vacancies")
    suspend fun getAllVacancies(): Response<List<Vacancy>>

    @GET("employers")
    suspend fun getAllEmployers(): Response<List<Employer>>

    @GET("user/me")
    suspend fun getUserInfo(
            @Header("token") token: String
    ): Response<User>

    @POST("user/signup")
    suspend fun register(
            @Body user: User
    ): Response<LoginResponse>

    @POST("user/login")
    suspend fun login(
            @Body request: LoginRequest
    ): Response<LoginResponse>

    @POST("vacancies/getCompanyName")
    suspend fun getCompanyName(
            @Body employerID: EmployerID
    ): Response<CompanyName>

    @POST("user/checkMail")
    suspend fun checkMail(
            @Body request: CheckMailRequest
    ): Response<CheckMailResponse>
}