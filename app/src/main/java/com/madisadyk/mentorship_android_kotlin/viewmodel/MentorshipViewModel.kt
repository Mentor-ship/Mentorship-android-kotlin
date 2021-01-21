package com.madisadyk.mentorship_android_kotlin.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.madisadyk.mentorship_android_kotlin.model.CheckMailResponse
import com.madisadyk.mentorship_android_kotlin.model.LoginResponse
import com.madisadyk.mentorship_android_kotlin.repositorie.MentorshipRepository
import com.madisadyk.mentorship_android_kotlin.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class MentorshipViewModel(
    val repository: MentorshipRepository
) : ViewModel() {

    val loginResponse: MutableLiveData<Resource<LoginResponse>> = MutableLiveData()
    val checkMailResponse: MutableLiveData<Resource<CheckMailResponse>> = MutableLiveData()


    fun login(email: String, password: String) = viewModelScope.launch {
        loginResponse.postValue(Resource.Loading())
        val response = repository.login(email, password)
        loginResponse.postValue(handleLoginResponse(response))
    }

    fun checkMail(email: String) = viewModelScope.launch {
        checkMailResponse.postValue(Resource.Loading())
        val response = repository.checkMail(email)
        checkMailResponse.postValue(handleCheckMailResponse(response))
    }

    private fun handleLoginResponse(response: Response<LoginResponse>): Resource<LoginResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    private fun handleCheckMailResponse(response: Response<CheckMailResponse>): Resource<CheckMailResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
}