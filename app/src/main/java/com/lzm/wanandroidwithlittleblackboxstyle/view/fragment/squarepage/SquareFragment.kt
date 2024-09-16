package com.lzm.wanandroidwithlittleblackboxstyle.view.fragment.squarepage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.lzm.wanandroidwithlittleblackboxstyle.R
import com.lzm.wanandroidwithlittleblackboxstyle.view.adapter.FragmentAdapter
import com.lzm.wanandroidwithlittleblackboxstyle.view.fragment.BaseFragment
import com.lzm.wanandroidwithlittleblackboxstyle.view.fragment.accountpage.AccountFragment
import com.lzm.wanandroidwithlittleblackboxstyle.viewmodel.BaseViewModel
import org.slf4j.LoggerFactory

class SquareFragment(viewModel: BaseViewModel) : BaseFragment(viewModel) {
    private val logger: org.slf4j.Logger = LoggerFactory.getLogger("SquareFragmentPage")

    private val fragments: List<Fragment> by lazy {
        listOf(ColumnFragment(), QesAndAnswFragment(), RouteFragment())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        logger.info("初始化")
        return inflater.inflate(R.layout.fragment_square, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        logger.info("执行视图")
        super.onViewCreated(view, savedInstanceState)
        val viewPage:ViewPager2 = view.findViewById(R.id.viewPager2)
        this.viewPager2Adapter=FragmentAdapter(requireActivity(),fragments)
        viewPage.adapter = viewPager2Adapter
    }

}