package com.lzm.wanandroidwithlittleblackboxstyle.view.fragment.homepage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lzm.wanandroidwithlittleblackboxstyle.R
import com.lzm.wanandroidwithlittleblackboxstyle.databinding.FragmentRecommend2Binding
import com.lzm.wanandroidwithlittleblackboxstyle.viewmodel.BaseViewModel


class RecommendArticleFragment(viewModel: BaseViewModel) : Fragment() {

    private var _binding: FragmentRecommend2Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecommend2Binding.inflate(inflater, container, false)
        return binding.root
    }






    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}