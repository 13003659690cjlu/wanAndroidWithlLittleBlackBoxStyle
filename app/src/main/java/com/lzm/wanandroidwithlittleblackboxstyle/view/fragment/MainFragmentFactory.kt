package com.lzm.wanandroidwithlittleblackboxstyle.view.fragment

import androidx.fragment.app.Fragment
import com.lzm.wanandroidwithlittleblackboxstyle.view.fragment.accountpage.AccountFragment
import com.lzm.wanandroidwithlittleblackboxstyle.view.fragment.homepage.HomeFragment
import com.lzm.wanandroidwithlittleblackboxstyle.view.fragment.projectpage.ProjectFragment
import com.lzm.wanandroidwithlittleblackboxstyle.view.fragment.squarepage.SquareFragment
import com.lzm.wanandroidwithlittleblackboxstyle.viewmodel.BaseViewModel
import org.slf4j.LoggerFactory

class MainFragmentFactory {
    private val logger: org.slf4j.Logger? = LoggerFactory.getLogger(MainFragmentFactory::class.java)

    public fun getMainFragment(positon:Int):Fragment{
       return when(positon){
            0 -> HomeFragment(BaseViewModel())
            1 -> SquareFragment(BaseViewModel())
            2 -> ProjectFragment(BaseViewModel())
            else -> AccountFragment(BaseViewModel())
        }
    }
}