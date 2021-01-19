package com.madisadyk.mentorship_android_kotlin.viewmodel

import androidx.lifecycle.ViewModel
import com.madisadyk.mentorship_android_kotlin.repositorie.MentorshipRepository

class MentorshipViewModel(
    val repository: MentorshipRepository
) : ViewModel() {
}