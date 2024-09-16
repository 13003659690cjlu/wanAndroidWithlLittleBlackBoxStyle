package com.lzm.wanandroidwithlittleblackboxstyle.utils

import com.google.android.material.tabs.TabLayout
import org.slf4j.LoggerFactory

class SquarepageAssemStrategyImpl:ITopTabAssemblyStrategy {
    private val logger: org.slf4j.Logger= LoggerFactory.getLogger("SquarepageAssemStrategyImpl")
    override fun AssemblyStrategy(leftTab: TabLayout, rightTab: TabLayout) {
        leftTab.removeAllTabs()
        val buttonTitleArray= arrayOf("answ","column","route")
        for(index in buttonTitleArray.indices){
            logger.info("装配按钮 ${buttonTitleArray[index]}")
            val tab = leftTab.newTab()
            tab.setText(buttonTitleArray.get(index))
            leftTab.addTab(tab)
        }
    }
}