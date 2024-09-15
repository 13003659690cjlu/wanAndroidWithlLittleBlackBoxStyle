package com.lzm.wanandroidwithlittleblackboxstyle

import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.lzm.wanandroidwithlittleblackboxstyle.utils.AssemTopTab
import com.lzm.wanandroidwithlittleblackboxstyle.view.TabButtomFactory
import com.lzm.wanandroidwithlittleblackboxstyle.view.fragment.MainFragmentFactory
import com.lzm.wanandroidwithlittleblackboxstyle.databinding.ActivityMainBinding
import com.lzm.wanandroidwithlittleblackboxstyle.utils.TopTabAddemManager
import org.slf4j.LoggerFactory

class MainActivity : AppCompatActivity(), OnTabSelectedListener{

    private val logger: org.slf4j.Logger = LoggerFactory.getLogger("mainpage")

    val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater) }

    val mainFragmentFactory:MainFragmentFactory by lazy {
        MainFragmentFactory()
    }

    val fragmentManager: FragmentManager by lazy {
        supportFragmentManager
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = binding.root
        setContentView(view)

        initTablayout()
        binding.tablayoutCuttomTab.addOnTabSelectedListener(this)
        initMainFragment()
    }


    fun initTablayout(){

        binding.leftTopTab.tabMode = TabLayout.MODE_SCROLLABLE
        binding.leftTopTab.tabGravity = TabLayout.GRAVITY_START

        //装配底部按钮
        val buttonTitleArray= arrayOf("首页","广场","项目","我")
        val iconArray:IntArray= intArrayOf(R.drawable.home,R.drawable.square,R.drawable.project,R.drawable.account)
        val tabButtomFactory=TabButtomFactory(this,binding.tablayoutCuttomTab)
        for(index in buttonTitleArray.indices){
            binding.tablayoutCuttomTab.addTab(tabButtomFactory.getTabItem(buttonTitleArray.get(index),iconArray.get(index)))
        }
    }

    fun initMainFragment(){
        binding.tablayoutCuttomTab.post {
            val tab_1 = binding.tablayoutCuttomTab.getTabAt(1)
            tab_1?.select()
            val tab_0 = binding.tablayoutCuttomTab.getTabAt(0)
            tab_0?.select()
        }
    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
        logger.info("${tab?.position}")
        var currentFragment:Fragment?
        if (tab!=null){

            //装配不同的fragment
            val position = tab.position
            val tag = "fragment_" + tab.position
            currentFragment=fragmentManager.findFragmentByTag(tag)

            if(currentFragment==null){
                currentFragment=mainFragmentFactory.getMainFragment(position)
                fragmentManager.beginTransaction().replace(R.id.fragment_container,currentFragment,tag).commit()
            }else{
                fragmentManager.beginTransaction().show(currentFragment).commit()
            }

            //根据不同fragment装配不同的顶部按钮
            var assemTopTab:AssemTopTab =when(position){
                0->AssemTopTab(TopTabAddemManager.homepageAssemStrategyImpl,binding.leftTopTab,binding.rightTopTab)
                1->AssemTopTab(TopTabAddemManager.squarepageAssemStrategyImpl,binding.leftTopTab,binding.rightTopTab)
                2->AssemTopTab(TopTabAddemManager.projectpageAssemStrategyImpl,binding.leftTopTab,binding.rightTopTab)
                else->AssemTopTab(TopTabAddemManager.accountpageAssemStrategyImpl,binding.leftTopTab,binding.rightTopTab)
            }

            assemTopTab.assemTopTab(binding.leftTopTab,binding.rightTopTab)


            //按钮被点击时改变颜色
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