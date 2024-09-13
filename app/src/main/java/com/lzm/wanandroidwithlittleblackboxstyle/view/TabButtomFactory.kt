package com.lzm.wanandroidwithlittleblackboxstyle.view

import android.content.Context
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.google.android.material.tabs.TabItem
import com.google.android.material.tabs.TabLayout
import com.lzm.wanandroidwithlittleblackboxstyle.R

class TabButtomFactory(val context:Context,val tabLayout: TabLayout) {

    public fun getTabItem(title:String,icon:Int):TabLayout.Tab{
        val view = LayoutInflater.from(context).inflate(R.layout.tab_item,null)
        val image:ImageView= view.findViewById(R.id.icon)
        val text:TextView = view.findViewById(R.id.title)
        image.setImageResource(icon)
        text.setText(title)
        val tab = tabLayout.newTab()
        tab.setCustomView(view)
        return tab
    }
}