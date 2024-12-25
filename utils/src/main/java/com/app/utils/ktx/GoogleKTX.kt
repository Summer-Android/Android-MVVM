package com.app.utils.ktx

import android.content.Context
import android.content.Intent
import android.net.Uri

/**
 * 跳转到GooglePlay商店对应的应用下
 */
fun Context.openGooglePlay(packageName: String?) {
    try {
        if (packageName != null) {
            Uri.parse("market://details?id=$packageName")?.let { uri ->
                val intent = Intent(Intent.ACTION_VIEW, uri)
                intent.setPackage("com.android.vending")
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            }
        }
    } catch (e: Exception) {
        e.printStackTrace()
        try {
            Uri.parse("https://play.google.com/store/apps/details?id=$packageName")?.let { uri ->
                val intent = Intent(Intent.ACTION_VIEW, uri)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}