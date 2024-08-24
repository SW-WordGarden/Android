package com.sw.wordgarden.presentation.util

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.fragment.app.Fragment

object KeyboardCleaner {
    fun setup(view: View, fragment: Fragment) {
        if (view !is EditText) {
            view.setOnTouchListener { v, _ ->
                hideKeyboard(fragment)
                v.performClick()
                v.clearFocus()
                false
            }
        }

        if (view is ViewGroup) {
            for (i in 0 until view.childCount) {
                val innerView = view.getChildAt(i)
                setup(innerView, fragment)
            }
        }
    }

    private fun hideKeyboard(fragment: Fragment) {
        val imm = fragment.activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        val currentFocus = fragment.activity?.currentFocus
        currentFocus?.let {
            imm.hideSoftInputFromWindow(it.windowToken, 0)
        }
    }
}