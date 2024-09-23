package com.lzm.wanandroidwithlittleblackboxstyle.view.fragment.homepage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.lzm.wanandroidwithlittleblackboxstyle.R
import com.lzm.wanandroidwithlittleblackboxstyle.view.adapter.FragmentAdapter
import com.lzm.wanandroidwithlittleblackboxstyle.view.fragment.BaseFragment
import com.lzm.wanandroidwithlittleblackboxstyle.viewmodel.BaseViewModel
import org.slf4j.LoggerFactory


class HomeFragment(viewModel: BaseViewModel) : BaseFragment(viewModel) {
    private val logger: org.slf4j.Logger = LoggerFactory.getLogger(HomeFragment::class.java)


    val leftTabLayout: TabLayout by lazy {
        requireActivity().findViewById(R.id.left_top_tab)
    }

    val rightTabLayout: TabLayout by lazy {
        requireActivity().findViewById(R.id.left_top_tab)
    }


    private val fragments: List<Fragment> by lazy {
        listOf(RecommendArticleFragment(viewModel))
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        logger.info("onViewCreated")
        super.onViewCreated(view, savedInstanceState)
        val viewPage: ViewPager2 = view.findViewById(R.id.viewPager2)
        this.viewPager2Adapter= FragmentAdapter(requireActivity(),fragments)
        viewPage.adapter = viewPager2Adapter

        leftTabLayout.removeAllTabs()
//        装配顶部tablayout
        val buttonTitleArray= arrayOf("推荐")
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