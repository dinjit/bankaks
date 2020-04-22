package com.example.bankaks.util

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

/**
 * Utility methods for manipulating the onscreen keyboard
 */
object KeyboardUtil {
    /**
     * Hides the soft keyboard
     */
    fun hideSoftKeyboard(activity: Activity) {
        val focusedView: View? = activity.currentFocus
        focusedView?.let {
            val inputMethodManager: InputMethodManager =
                activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(it.windowToken, 0)
        }
    }

    /**
     * Shows the soft keyboard
     */
    fun showSoftKeyboard(view: View) {
        val inputMethodManager: InputMethodManager =
            view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        view.requestFocus()
        inputMethodManager.showSoftInput(view, 0)
    }
}