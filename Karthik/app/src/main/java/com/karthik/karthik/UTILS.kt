package com.karthik.karthik

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

class UTILS

    (private val context: Context) {
    fun isEmpty(textInputEditText: EditText, message: String): Boolean {
        val value = textInputEditText.text.toString().trim()
        if (value.isEmpty()) {
            textInputEditText.error = message
            keyboard(textInputEditText)
            return false
        } else {
            textInputEditText.error = ""
        }

        return true
    }
    fun isEmail(textInputEditText: EditText, message: String): Boolean {
        val value = textInputEditText.text.toString().trim()
        if (value.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(value).matches()) {
            textInputEditText.error = message
            keyboard(textInputEditText)
            return false
        } else
        {
            textInputEditText.error = ""
        }
        return true
    }
    private fun keyboard(view: View) {
        val imm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
    }
}