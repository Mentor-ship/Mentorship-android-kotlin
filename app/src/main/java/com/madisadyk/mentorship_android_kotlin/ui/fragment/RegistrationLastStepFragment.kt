package com.madisadyk.mentorship_android_kotlin.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.google.android.material.textfield.TextInputLayout
import com.madisadyk.mentorship_android_kotlin.R
import com.madisadyk.mentorship_android_kotlin.model.User
import com.madisadyk.mentorship_android_kotlin.ui.MainActivity
import com.madisadyk.mentorship_android_kotlin.utils.Constant.Companion.CITIES
import com.madisadyk.mentorship_android_kotlin.utils.Constant.Companion.COUNTRIES
import com.madisadyk.mentorship_android_kotlin.utils.Resource
import com.madisadyk.mentorship_android_kotlin.utils.SessionManager
import com.madisadyk.mentorship_android_kotlin.viewmodel.MentorshipViewModel
import kotlinx.android.synthetic.main.fragment_register_last_step.*

class RegistrationLastStepFragment : Fragment(R.layout.fragment_register_last_step) {

    lateinit var viewModel: MentorshipViewModel

    lateinit var sessionManager: SessionManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        sessionManager = SessionManager(activity as MainActivity)

        configureAutoCompleteViews()
        setEmptyListeners()

        viewModel.registerResponse.observe(viewLifecycleOwner, Observer { response ->
            when(response) {
                is Resource.Success -> {
                    sessionManager.saveToken(response.data?.token)
                    findNavController().navigate(R.id.action_registrationLastStepFragment_to_homeFragment)
                }
            }
        })

        button_register.setOnClickListener {
            val fields: Array<EditText> = arrayOf(country_edit_text, city_edit_text, phone_edit_text, first_name_edit_text, last_name_edit_text)
            val layouts : Array<TextInputLayout> = arrayOf(country_input_layout, city_input_layout, phone_input_layout, first_name_input_layout, last_name_input_layout)

            if ((activity as MainActivity).validate((activity as MainActivity), fields, layouts)) {
                viewModel.register(User(
                        viewModel.name4reg,
                        viewModel.email4reg,
                        phone_edit_text.text.toString(),
                        viewModel.password4reg,
                        first_name_edit_text.text.toString(),
                        last_name_edit_text.text.toString(),
                        country_edit_text.text.toString(),
                        city_edit_text.text.toString()))
            }
        }
    }

    private fun configureAutoCompleteViews() {
        setAdapterToView(country_edit_text, COUNTRIES)
        country_edit_text.onItemClickListener = OnItemClickListener { parent, view, position, id ->
            setAdapterToView(city_edit_text, CITIES[position]) }
    }

    private fun setAdapterToView(view: MaterialAutoCompleteTextView, array: Array<String>) {
        val arrayAdapter = ArrayAdapter((activity as MainActivity),
                R.layout.custom_dropdown_list_item, array)
        view.setAdapter(arrayAdapter)
    }

    private fun setEmptyListeners() {
        (activity as MainActivity).createEmptyTextListener((activity as MainActivity),
                country_edit_text,
                country_input_layout)
        (activity as MainActivity).createEmptyTextListener((activity as MainActivity),
                city_edit_text,
                city_input_layout)
        (activity as MainActivity).createEmptyTextListener((activity as MainActivity),
                phone_edit_text,
                phone_input_layout)
        (activity as MainActivity).createEmptyTextListener((activity as MainActivity),
                first_name_edit_text,
                first_name_input_layout)
        (activity as MainActivity).createEmptyTextListener((activity as MainActivity),
                last_name_edit_text,
                last_name_input_layout)
    }
}