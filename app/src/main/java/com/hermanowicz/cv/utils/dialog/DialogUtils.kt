package com.hermanowicz.cv.utils.dialog

import android.app.AlertDialog
import android.content.Context
import com.hermanowicz.cv.R


fun showAlertWithMessage(context: Context, remove: () -> Unit) {
    val dialogBuilder = AlertDialog.Builder(context)
    dialogBuilder.setMessage(context.getString(R.string.removeConfirmation))
        .setCancelable(true)
        .setPositiveButton("OK") { dialogInterface, _ ->
            remove()
            dialogInterface.dismiss()
        }
        .setNegativeButton("NO") { dialogInterface, _ -> dialogInterface.cancel() }
    dialogBuilder.create().show()
}

fun showErrorDialog(context: Context, message: String? = "") {
    val dialogBuilder = AlertDialog.Builder(context)
    dialogBuilder.setMessage(message)
        .setPositiveButton("OK") { dialogInterface, _ ->
            dialogInterface.dismiss()
        }
    dialogBuilder.create().show()
}
