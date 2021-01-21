package com.madisadyk.mentorship_android_kotlin.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.madisadyk.mentorship_android_kotlin.model.LoginResponse
import com.madisadyk.mentorship_android_kotlin.repositorie.MentorshipRepository
import com.madisadyk.mentorship_android_kotlin.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class MentorshipViewModel(
    val repository: MentorshipRepository
) : ViewModel() {

    val loginResponse: MutableLiveData<Resource<LoginResponse>> = MutableLiveData()


    fun login(email: String, password: String) = viewModelScope.launch {
        loginResponse.postValue(Resource.Loading())
        val response = repository.login(email, password)
        loginResponse.postValue(handleLoginResponse(response))
    }

    private fun handleLoginResponse(response: Response<LoginResponse>): Resource<LoginResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponde ->
                return Resource.Success(resultResponde)
            }
        }
        return Resource.Error(response.message())
    }
}