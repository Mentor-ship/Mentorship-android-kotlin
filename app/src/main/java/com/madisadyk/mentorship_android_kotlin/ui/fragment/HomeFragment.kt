package com.madisadyk.mentorship_android_kotlin.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import com.madisadyk.mentorship_android_kotlin.R
import com.madisadyk.mentorship_android_kotlin.ui.MainActivity
import com.madisadyk.mentorship_android_kotlin.utils.SessionManager
import com.madisadyk.mentorship_android_kotlin.viewmodel.MentorshipViewModel
import kotlinx.android.synthetic.main.activity_main.*

class HomeFragment : Fragment(R.layout.fragment_home) {

    lateinit var viewModel: MentorshipViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel

        if (SessionManager.fetchToken(activity as MainActivity) == null) {
            findNavController().navigate(R.id.action_homeFragment_to_loginFragment)
            (activity as MainActivity).nav_bar.visibility = View.GONE
        }
    }
}