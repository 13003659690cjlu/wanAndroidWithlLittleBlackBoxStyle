package com.lzm.wanandroidwithlittleblackboxstyle.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.viewpager2.widget.ViewPager2
import com.lzm.wanandroidwithlittleblackboxstyle.R
import com.lzm.wanandroidwithlittleblackboxstyle.view.adapter.FragmentAdapter
import com.lzm.wanandroidwithlittleblackboxstyle.viewmodel.BaseViewModel

open class BaseFragment(val viewModel: BaseViewModel) : Fragment() {

    public lateinit var viewPager2Adapter: FragmentAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_base, container, false)
    }

}