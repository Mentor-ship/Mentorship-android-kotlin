package com.madisadyk.mentorship_android_kotlin.data.remote

import com.madisadyk.mentorship_android_kotlin.data.model.*
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(
    private val apiService: ApiService
) : ApiHelper{

    override suspend fun getAllVacancies(): Response<List<Vacancy>> = apiService.getAllVacancies()

    override suspend fun getAllEmployers(): Response<List<Employer>> = apiService.getAllEmployers()

    override suspend fun getUserInfo(token: String): Response<User> = apiService.getUserInfo(token)

    override suspend fun register(user: User): Response<LoginResponse> = apiService.register(user)

    override suspend fun login(request: LoginRequest): Response<LoginResponse> = apiService.login(request)

    override suspend fun getCompanyName(employerID: EmployerID): Response<CompanyName> = apiService.getCompanyName(employerID)

    override suspend fun checkMail(request: CheckMailRequest): Response<CheckMailResponse> = apiService.checkMail(request)
}