package com.aymen.cafeyn.global.extension

import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

fun Context?.isNetworkAvailable(): Boolean =
    this?.let {
        val connectivityManager =
            this.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork ?: return false
            val netCapabilities =
                connectivityManager.getNetworkCapabilities(network) ?: return false
            return when {
                netCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                netCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false
            }
        } else {

            val netInfo = connectivityManager.activeNetworkInfo ?: return false
            return netInfo.isConnected
        }
    } ?: run {
        return false
    }