package com.madisadyk.mentorship_android_kotlin.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.madisadyk.mentorship_android_kotlin.model.*
import com.madisadyk.mentorship_android_kotlin.repositorie.MentorshipRepository
import com.madisadyk.mentorship_android_kotlin.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class MentorshipViewModel(
    val repository: MentorshipRepository
) : ViewModel() {

    val loginResponse: MutableLiveData<Resource<LoginResponse>> = MutableLiveData()
    val checkMailResponse: MutableLiveData<Resource<CheckMailResponse>> = MutableLiveData()
    val registerResponse: MutableLiveData<Resource<LoginResponse>> = MutableLiveData()
    val vacancyResponse: MutableLiveData<Resource<List<Vacancy>>> = MutableLiveData()

    lateinit var name4reg: String
    lateinit var password4reg: String
    lateinit var email4reg: String

    init {
        getAllVacancies()
    }


    fun login(email: String, password: String) = viewModelScope.launch {
        loginResponse.postValue(Resource.Loading())
        val response = repository.login(email, password)
        loginResponse.postValue(handleResponse(response))
    }

    fun checkMail(email: String) = viewModelScope.launch {
        checkMailResponse.postValue(Resource.Loading())
        val response = repository.checkMail(email)
        checkMailResponse.postValue(handleResponse(response))
    }

    fun register(user: User) = viewModelScope.launch {
        registerResponse.postValue(Resource.Loading())
        val response = repository.register(user)
        registerResponse.postValue(handleResponse(response))
    }
    //при росте ваканций нужна пагинация
    fun getAllVacancies() = viewModelScope.launch {
        vacancyResponse.postValue(Resource.Loading())
        val response = repository.getAllVacancies()
        vacancyResponse.postValue(handleResponse(response))
    }

    private fun <T> handleResponse(response: Response<T>): Resource<T> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
}
