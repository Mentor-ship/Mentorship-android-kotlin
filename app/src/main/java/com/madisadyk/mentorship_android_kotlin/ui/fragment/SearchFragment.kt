package com.madisadyk.mentorship_android_kotlin.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.madisadyk.mentorship_android_kotlin.R
import com.madisadyk.mentorship_android_kotlin.ui.MainActivity
import com.madisadyk.mentorship_android_kotlin.ui.viewmodel.MentorshipViewModel

class SearchFragment : Fragment(R.layout.fragment_search) {
    lateinit var viewModel: MentorshipViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
    }
}