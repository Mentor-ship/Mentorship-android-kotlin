package com.madisadyk.mentorship_android_kotlin.ui

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.textfield.TextInputLayout
import com.madisadyk.mentorship_android_kotlin.R
import com.madisadyk.mentorship_android_kotlin.ui.viewmodel.MentorshipViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    val viewModel: MentorshipViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nav_bar.setupWithNavController(navHostFragment.findNavController())
    }

    fun createEmptyTextListener(
        context: Context,
        editText: EditText,
        inputLayout: TextInputLayout
    ) {
        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                if (s.isEmpty()) {
                    inputLayout.error = context.getString(R.string.field_is_required)
                } else {
                    inputLayout.isErrorEnabled = false
                }
            }
        })
    }

    fun validate(
        context: Context,
        fields: Array<EditText>,
        layouts: Array<TextInputLayout>
    ): Boolean {
        var result = true
        for (i in fields.indices) {
            if (fields[i].length() == 0) {
                layouts[i].error = context.getString(R.string.field_is_required)
                result = false
            }
        }
        return result
    }
}