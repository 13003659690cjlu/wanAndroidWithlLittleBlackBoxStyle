package com.lzm.wanandroidwithlittleblackboxstyle.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object TimeUtil {
    fun millisToDateTime(millis: Long): String {
        val sdf = SimpleDateFormat("YYYY-MM-dd HH:mm", Locale.getDefault())
        return sdf.format(Date(millis))
    }

}