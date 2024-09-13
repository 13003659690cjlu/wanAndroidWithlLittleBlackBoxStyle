package com.lzm.wanandroidwithlittleblackboxstyle.view.fragment

import androidx.fragment.app.Fragment
import com.lzm.wanandroidwithlittleblackboxstyle.view.fragment.accountpage.AccountFragment
import com.lzm.wanandroidwithlittleblackboxstyle.view.fragment.homepage.HomeFragment
import com.lzm.wanandroidwithlittleblackboxstyle.view.fragment.projectpage.ProjectFragment
import com.lzm.wanandroidwithlittleblackboxstyle.view.fragment.squarepage.SquareFragment

class MainFragmentFactory {

    public fun getMainFragment(positon:Int):Fragment{
       return when(positon){
            0 -> HomeFragment()
            1 -> ProjectFragment()
            2 -> SquareFragment()
            else -> AccountFragment()
        }
    }
}