package com.lzm.wanandroidwithlittleblackboxstyle.utils

import com.google.android.material.tabs.TabLayout
import com.lzm.wanandroidwithlittleblackboxstyle.R
import com.lzm.wanandroidwithlittleblackboxstyle.view.TabButtomFactory
import org.slf4j.LoggerFactory

class HomepageAssemStrategyImpl:ITopTabAssemblyStrategy {
    private val logger: org.slf4j.Logger? = LoggerFactory.getLogger(HomepageAssemStrategyImpl::class.java)
    override fun AssemblyStrategy(leftTab: TabLayout, rightTab: TabLayout) {
        leftTab.removeAllTabs()
        val buttonTitleArray= arrayOf("推荐")
        for(index in buttonTitleArray.indices){
            val tab = leftTab.newTab()
            tab.setText(buttonTitleArray.get(index))
            leftTab.addTab(tab)
        }
    }
}