package com.lzm.wanandroidwithlittleblackboxstyle.view.fragment.squarepage

import android.content.res.ColorStateList
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.lzm.wanandroidwithlittleblackboxstyle.R
import com.lzm.wanandroidwithlittleblackboxstyle.utils.AssemTopTab
import com.lzm.wanandroidwithlittleblackboxstyle.utils.TopTabAddemManager
import com.lzm.wanandroidwithlittleblackboxstyle.view.adapter.FragmentAdapter
import com.lzm.wanandroidwithlittleblackboxstyle.view.fragment.BaseFragment
import com.lzm.wanandroidwithlittleblackboxstyle.view.fragment.accountpage.AccountFragment
import com.lzm.wanandroidwithlittleblackboxstyle.viewmodel.BaseViewModel
import org.slf4j.LoggerFactory

class SquareFragment(viewModel: BaseViewModel) : BaseFragment(viewModel) {
    private val logger: org.slf4j.Logger = LoggerFactory.getLogger("SquareFragmentPage")
    val leftTabLayout: TabLayout by lazy {
        requireActivity().findViewById(R.id.left_top_tab)
    }

    val rightTabLayout: TabLayout by lazy {
        requireActivity().findViewById(R.id.left_top_tab)
    }


    private val fragments: List<Fragment> by lazy {
        listOf(ColumnFragment(), QesAndAnswFragment(), RouteFragment())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        logger.info("onCreateView")
        return inflater.inflate(R.layout.fragment_square, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        logger.info("onViewCreated")
        super.onViewCreated(view, savedInstanceState)
        val viewPage:ViewPager2 = view.findViewById(R.id.viewPager2)
        this.viewPager2Adapter=FragmentAdapter(requireActivity(),fragments)
        viewPage.adapter = viewPager2Adapter

        leftTabLayout.removeAllTabs()
//        装配顶部tablayout
        val buttonTitleArray= arrayOf("answ","column","route")
//        viewpage绑定tablayout
        TabLayoutMediator(leftTabLayout,viewPage){tab,position ->
            tab.setText(buttonTitleArray[position])
        }.attach()
        viewPage.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                leftTabLayout.getTabAt(position)?.select()
            }
        })
    }

}