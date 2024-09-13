package com.lzm.wanandroidwithlittleblackboxstyle

import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.lzm.wanandroidwithlittleblackboxstyle.view.TabButtomFactory
import com.lzm.wanandroidwithlittleblackboxstyle.view.fragment.MainFragmentFactory

class MainActivity : AppCompatActivity(), OnTabSelectedListener{



    val bottom_tabLayout:TabLayout by lazy {
        findViewById(R.id.tablayout_cuttom_tab)
    }

    val fragmentContainer:FrameLayout by lazy {
        findViewById(R.id.fragment_container)
    }

    val mainFragmentFactory:MainFragmentFactory by lazy {
        MainFragmentFactory()
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
        initBottomTablayout()
        initMainFragment()


        bottom_tabLayout.addOnTabSelectedListener(this)
    }


    fun initBottomTablayout(){
        //装配底部按钮
        val buttonTitleArray= arrayOf("首页","广场","项目","我")
        val iconArray:IntArray= intArrayOf(R.drawable.home,R.drawable.square,R.drawable.project,R.drawable.account)
        val tabButtomFactory=TabButtomFactory(this,bottom_tabLayout)
        for(index in buttonTitleArray.indices){
            bottom_tabLayout.addTab(tabButtomFactory.getTabItem(buttonTitleArray.get(index),iconArray.get(index)))
        }
    }

    fun initMainFragment(){
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, mainFragmentFactory.getMainFragment(0))
            .commit()
    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
        if (tab!=null){
            val position = tab.position
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container,mainFragmentFactory.getMainFragment(position))
                .commit()
        }

        if (tab != null) {
            val customView = tab.customView
            val textView= customView?.findViewById<TextView>(R.id.title)
            textView?.setTextColor(Color.RED)

            val imageView = customView?.findViewById<ImageView>(R.id.icon)
            imageView?.setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN) // 选中时变为红色

        }

    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {
        if (tab != null) {
            val customView = tab.customView
            val textView= customView?.findViewById<TextView>(R.id.title)
            textView?.setTextColor(Color.BLACK)

            val imageView = customView?.findViewById<ImageView>(R.id.icon)
            imageView?.clearColorFilter()

        }
    }

    override fun onTabReselected(tab: TabLayout.Tab?) {

    }
}