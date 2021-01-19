package com.madisadyk.mentorship_android_kotlin.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.madisadyk.mentorship_android_kotlin.R
import com.madisadyk.mentorship_android_kotlin.repositorie.MentorshipRepository
import com.madisadyk.mentorship_android_kotlin.viewmodel.MentorshipViewModel
import com.madisadyk.mentorship_android_kotlin.viewmodel.MentorshipViewModelProviderFactory

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MentorshipViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repository = MentorshipRepository()
        val viewModelProviderFactory = MentorshipViewModelProviderFactory(repository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory)
            .get(MentorshipViewModel::class.java)
    }
}