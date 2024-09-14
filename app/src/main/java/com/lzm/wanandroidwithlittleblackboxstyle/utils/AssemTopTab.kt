package com.lzm.wanandroidwithlittleblackboxstyle.utils

import com.google.android.material.tabs.TabLayout
import org.slf4j.LoggerFactory

class AssemTopTab(val topAssemStrate: ITopTabAssemblyStrategy,
                  val leftTabLayout: TabLayout,
                  val rightTabLayout: TabLayout) {
//    private val logger: org.slf4j.Logger? = LoggerFactory.getLogger(AssemTopTab::class.java)


    public fun assemTopTab(leftTopTab: TabLayout, rightTopTab: TabLayout) {
        topAssemStrate.AssemblyStrategy(leftTabLayout,rightTabLayout)
    }
}
