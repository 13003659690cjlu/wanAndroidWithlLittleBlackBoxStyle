package com.lzm.wanandroidwithlittleblackboxstyle.view.fragment.homepage

import android.nfc.tech.MifareUltralight.PAGE_SIZE
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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

    private val articles: MutableList<ArticleItem> by lazy { mutableListOf() }
    private val articleAdapter by lazy { ArticleAdapter(articles) }
    private var page = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecommend2Binding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.articlesRecycleView.layoutManager = LinearLayoutManager(requireContext())
        binding.articlesRecycleView.adapter = articleAdapter
        articlesViewModel.getArticles(page)
        articlesViewModel.articlesData.observe(viewLifecycleOwner, { newArticles ->
            articles.addAll(newArticles)
            articleAdapter.notifyDataSetChanged()
        })

        //上划到底部加载
        binding.articlesRecycleView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                    && firstVisibleItemPosition >= 0
                    && totalItemCount >= PAGE_SIZE
                ) {
                    // 滑动到底部执行加载更多数据的操作
                    articlesViewModel.getArticles(++page)
                }

            }
        })
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}