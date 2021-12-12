package com.andrei.hearthstoneassesment.domain.util

import android.os.Build

inline fun <T> sdk28AndUp(callback: () -> T): T? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
        callback()
    } else {
        null
    }
}