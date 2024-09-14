package com.lzm.wanandroidwithlittleblackboxstyle.utils

import com.google.android.material.tabs.TabLayout
import org.slf4j.LoggerFactory

class AccountpageAssemStrategyImpl:ITopTabAssemblyStrategy {
    private val logger: org.slf4j.Logger? = LoggerFactory.getLogger(AccountpageAssemStrategyImpl::class.java)
    override fun AssemblyStrategy(leftTab: TabLayout, rightTab: TabLayout) {
        leftTab.removeAllTabs()
        val buttonTitleArray= arrayOf("账户")
        for(index in buttonTitleArray.indices){
            val tab = leftTab.newTab()
            tab.setText(buttonTitleArray.get(index))
            leftTab.addTab(tab)
        }
    }
}