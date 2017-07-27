package com.example.ranosys.autestingdemo.utils

import android.graphics.Color
import android.support.design.widget.Snackbar
import android.view.View
import android.widget.TextView

/**
 * @author Hitesh Khatri
 */
object SnackBar {

    // A method for showing common snackBar in app.
    internal fun showSnackBar(view: View, snackBarText: String, color: Int) {
        val snackbar = Snackbar.make(view, snackBarText, Snackbar.LENGTH_LONG)
                .setActionTextColor(Color.WHITE)
        val snackBarView = snackbar.view
        snackBarView.setBackgroundColor(color)
        val textView = snackBarView.findViewById(
                android.support.design.R.id.snackbar_text) as TextView
        textView.setTextColor(Color.WHITE)
        snackbar.show()
    }

}
