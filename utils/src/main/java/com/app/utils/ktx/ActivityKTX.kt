package com.app.utils.ktx

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.core.content.IntentCompat
import androidx.core.os.BundleCompat

// ------------ Intent getParcelable ext ------------

inline fun <reified T : Any> Intent.getParcelableExtraCompat(key: String): T? {
    return IntentCompat.getParcelableExtra(this, key, T::class.java)
}

inline fun <reified T : Any> Intent.getParcelableArrayListExtraCompat(key: String): ArrayList<T>? {
    return IntentCompat.getParcelableArrayListExtra(this, key, T::class.java)
}

// ------------ Bundle getParcelable ext ------------

inline fun <reified T : Any> Bundle.getParcelableCompat(key: String): T? {
    return BundleCompat.getParcelable(this, key, T::class.java)
}

inline fun <reified T : Any> Bundle.getParcelableArrayListCompat(key: String): ArrayList<T>? {
    return BundleCompat.getParcelableArrayList(this, key, T::class.java)
}

fun Activity.isMultiWindowMode(): Boolean {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        this.isInMultiWindowMode
    } else false
}

fun Activity.isActivityForeground(): Boolean {
    return !this.isFinishing && !this.isDestroyed && this.window?.decorView?.isShown == true
}