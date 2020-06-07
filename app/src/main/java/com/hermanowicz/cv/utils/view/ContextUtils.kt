package com.hermanowicz.cv.utils.view

import android.content.Context
import androidx.core.content.ContextCompat


fun Context.hasPermissions(permission: String): Boolean =
    ContextCompat.checkSelfPermission(this, permission) == android.content.pm.PackageManager.PERMISSION_GRANTED
