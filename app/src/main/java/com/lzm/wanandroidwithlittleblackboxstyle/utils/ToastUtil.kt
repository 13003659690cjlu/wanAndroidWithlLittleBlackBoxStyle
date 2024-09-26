package com.lzm.wanandroidwithlittleblackboxstyle.utils

import android.content.Context
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast
import com.lzm.wanandroidwithlittleblackboxstyle.R

object ToastUtil {

    fun showCustomToast(context: Context, message: String) {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val layout = inflater.inflate(R.layout.custom_toast_layout, null)

        val textView = layout.findViewById<TextView>(R.id.toast_text)
        textView.text = message
        with(Toast(context)) {
            duration = Toast.LENGTH_SHORT
            view = layout
            show()
        }
    }
}