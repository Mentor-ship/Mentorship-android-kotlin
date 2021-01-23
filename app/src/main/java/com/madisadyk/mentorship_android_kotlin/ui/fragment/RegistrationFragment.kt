package com.madisadyk.mentorship_android_kotlin.ui.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputLayout
import com.madisadyk.mentorship_android_kotlin.R
import com.madisadyk.mentorship_android_kotlin.ui.MainActivity
import com.madisadyk.mentorship_android_kotlin.utils.Resource
import com.madisadyk.mentorship_android_kotlin.ui.viewmodel.MentorshipViewModel
import kotlinx.android.synthetic.main.fragment_register.*
import kotlinx.android.synthetic.main.fragment_register.button_login
import kotlinx.android.synthetic.main.fragment_register.password_input_layout

class RegistrationFragment : Fragment(R.layout.fragment_register) {

    lateinit var viewModel: MentorshipViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel

        setEmptyListeners()
        passwordListener()

        button_next.setOnClickListener {
            val fields: Array<EditText> = arrayOf(email_edit_text, username_edit_text, password_reg_edit_text, password_confirm_edit_text)
            val layouts : Array<TextInputLayout> = arrayOf(email_input_layout, username_input_layout, password_input_layout, password_confirm_input_layout)

            var hasError = false

            if (!(activity as MainActivity).validate((activity as MainActivity), fields, layouts)) {
                hasError = true
            }
            if (password_reg_edit_text.text.toString()
                != password_confirm_edit_text.text.toString()) {
                password_confirm_input_layout.error = getString(R.string.passwords_not_match)
                hasError = true
            }
            viewModel.checkMail(email_edit_text.text.toString())

            viewModel.checkMailResponse.observe(viewLifecycleOwner, Observer { response ->
                when(response) {
                    is Resource.Success -> {
                        if (response.data?.isRegistered!!) {
                            email_input_layout.error = getString(R.string.email_in_use)
                        }
                        else if (!hasError) {
                            viewModel.name4reg = username_edit_text.text.toString()
                            viewModel.email4reg = email_edit_text.text.toString()
                            viewModel.password4reg = password_reg_edit_text.text.toString()
                            findNavController().navigate(
                                R.id.action_registrationFragment_to_registrationLastStepFragment)
                        }
                    }
                }
            })
        }

        button_login.setOnClickListener {
            findNavController().navigate(R.id.loginFragment)
        }
    }

    private fun passwordListener() {
        password_confirm_edit_text.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.toString() != password_reg_edit_text.text.toString()) {
                    password_confirm_input_layout.error = getString(R.string.passwords_not_match)
                } else {
                    password_confirm_input_layout.isErrorEnabled = false
                }
            }

            override fun afterTextChanged(s: Editable?) {}

        })

        password_reg_edit_text.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (password_confirm_edit_text.text.toString().isEmpty()) {
                    password_confirm_input_layout.error = getString(R.string.passwords_not_match)
                } else {
                    password_confirm_input_layout.isErrorEnabled = false
                }
            }

            override fun afterTextChanged(s: Editable?) {}

        })
    }

    private fun setEmptyListeners() {
        (activity as MainActivity).createEmptyTextListener(
            (activity as MainActivity), email_edit_text, email_input_layout)
        (activity as MainActivity).createEmptyTextListener(
            (activity as MainActivity), username_edit_text, username_input_layout)
        (activity as MainActivity).createEmptyTextListener(
            (activity as MainActivity), password_reg_edit_text, password_input_layout
        )
    }
}