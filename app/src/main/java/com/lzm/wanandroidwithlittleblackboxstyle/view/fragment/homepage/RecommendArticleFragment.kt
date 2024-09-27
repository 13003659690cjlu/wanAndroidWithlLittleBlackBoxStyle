package com.lzm.wanandroidwithlittleblackboxstyle.view.fragment.homepage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.lzm.wanandroidwithlittleblackboxstyle.R
import com.lzm.wanandroidwithlittleblackboxstyle.databinding.FragmentRecommend2Binding
import com.lzm.wanandroidwithlittleblackboxstyle.model.bean.ArticleItem
import com.lzm.wanandroidwithlittleblackboxstyle.model.bean.ArticleTag
import com.lzm.wanandroidwithlittleblackboxstyle.view.adapter.ArticleAdapter
import com.lzm.wanandroidwithlittleblackboxstyle.viewmodel.ArticlesViewModel
import com.lzm.wanandroidwithlittleblackboxstyle.viewmodel.BaseViewModel


class RecommendArticleFragment(viewModel: BaseViewModel) : Fragment() {

    private var _binding: FragmentRecommend2Binding? = null
    private val binding get() = _binding!!
    private val articlesViewModel by lazy {
        ViewModelProvider(this).get(ArticlesViewModel::class.java)
    }

    private val articles:MutableList<ArticleItem> by lazy { mutableListOf() }
    private val articleAdapter by lazy { ArticleAdapter(articles) }
    private var page=39


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecommend2Binding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.articlesRecycleView.layoutManager=LinearLayoutManager(requireContext())
        binding.articlesRecycleView.adapter=articleAdapter
        articlesViewModel.getArticles(page)
        articlesViewModel.articlesData.observe(viewLifecycleOwner,{ newArticles ->
            articles.addAll(newArticles)
            articleAdapter.notifyDataSetChanged()
        })
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}