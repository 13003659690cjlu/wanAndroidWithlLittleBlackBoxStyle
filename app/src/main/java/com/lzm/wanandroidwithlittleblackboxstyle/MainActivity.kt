package com.lzm.wanandroidwithlittleblackboxstyle

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.tabs.TabLayout
import com.lzm.wanandroidwithlittleblackboxstyle.view.TabButtomFactory

class MainActivity : AppCompatActivity() {

    val tabLayout:TabLayout by lazy {
        findViewById(R.id.tablayout_cuttom_tab)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initButtomTsb()
    }


    fun initButtomTsb(){
        val titleArray= arrayOf("首页","广场","我","广场")
        val iconArray:IntArray= intArrayOf(R.drawable.home,R.drawable.square,R.drawable.project,R.drawable.account)
        val tabButtomFactory=TabButtomFactory(this,tabLayout)
        for(index in titleArray.indices){
            tabLayout.addTab(tabButtomFactory.getTabItem(titleArray.get(index),iconArray.get(index)))
        }

    }
}