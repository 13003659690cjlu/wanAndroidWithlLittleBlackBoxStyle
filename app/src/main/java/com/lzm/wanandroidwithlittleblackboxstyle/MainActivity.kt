package com.lzm.wanandroidwithlittleblackboxstyle

import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayoutMediator
import com.lzm.wanandroidwithlittleblackboxstyle.utils.AssemTopTab
import com.lzm.wanandroidwithlittleblackboxstyle.view.TabButtomFactory
import com.lzm.wanandroidwithlittleblackboxstyle.view.fragment.MainFragmentFactory
import com.lzm.wanandroidwithlittleblackboxstyle.databinding.ActivityMainBinding
import com.lzm.wanandroidwithlittleblackboxstyle.utils.TopTabAddemManager
import com.lzm.wanandroidwithlittleblackboxstyle.view.fragment.BaseFragment
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

        val fragmentContainer = binding.fragmentContainer
        val originalBottomMargin = (fragmentContainer.layoutParams as ConstraintLayout.LayoutParams).bottomMargin

        val rootView = binding.main
        rootView.viewTreeObserver.addOnGlobalLayoutListener {
            val heightDiff = rootView.rootView.height - rootView.height
            if (heightDiff > 100) { // 这个值可以根据需要调整
                // 键盘弹出时的处理
                val layoutParams = fragmentContainer.layoutParams as ConstraintLayout.LayoutParams
                layoutParams.bottomMargin = originalBottomMargin + heightDiff // 调整底部边距
                fragmentContainer.layoutParams = layoutParams
            } else {
                // 键盘隐藏时的处理
                val layoutParams = fragmentContainer.layoutParams as ConstraintLayout.LayoutParams
                layoutParams.bottomMargin = originalBottomMargin
                fragmentContainer.layoutParams = layoutParams
            }
        }

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
//                不销毁之前的fragment
                val fragmentTransaction = supportFragmentManager.beginTransaction()
                val fragments = supportFragmentManager.fragments
                for (fragment in fragments) {
                    if (fragment.tag == tag) {
                        fragmentTransaction.show(fragment)
                    } else {
                        fragmentTransaction.hide(fragment)
                    }
                }
                fragmentTransaction.commit()
            }

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