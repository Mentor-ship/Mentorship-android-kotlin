package com.madisadyk.mentorship_android_kotlin.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.madisadyk.mentorship_android_kotlin.repositorie.MentorshipRepository

class MentorshipViewModelProviderFactory(
    val repository: MentorshipRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MentorshipViewModel(repository) as T
    }
}