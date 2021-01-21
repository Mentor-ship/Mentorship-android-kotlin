package com.madisadyk.mentorship_android_kotlin.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.madisadyk.mentorship_android_kotlin.R
import com.madisadyk.mentorship_android_kotlin.ui.MainActivity
import com.madisadyk.mentorship_android_kotlin.utils.Resource
import com.madisadyk.mentorship_android_kotlin.utils.SessionManager
import com.madisadyk.mentorship_android_kotlin.viewmodel.MentorshipViewModel
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment(R.layout.fragment_login) {

    lateinit var viewModel: MentorshipViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel

        val sessionManager = SessionManager(activity as MainActivity)

        button_login.setOnClickListener {
            if (validateEmptyFields()) {
                viewModel.login(login_edit_text.text.toString(), password_edit_text.text.toString())
            }
        }

        viewModel.loginResponse.observe(viewLifecycleOwner, Observer { response ->
            when(response) {
                is Resource.Success -> {
                    sessionManager.saveToken(response.data?.token.toString())
                    findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                }
                is Resource.Error -> {}
                is Resource.Loading -> {}
            }
        })
    }

    private fun validateEmptyFields(): Boolean {
        var flag = true
        if (login_edit_text.text.toString().isEmpty()) {
            login_input_layout.error = R.string.field_is_required.toString()
            flag = false
        }
        if (password_edit_text.text.toString().isEmpty()) {
            password_input_layout.error = R.string.field_is_required.toString()
            flag = false
        }
        return flag
    }
}