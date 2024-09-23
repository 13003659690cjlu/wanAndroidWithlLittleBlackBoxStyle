package com.lzm.wanandroidwithlittleblackboxstyle.viewmodel

import com.lzm.wanandroidwithlittleblackboxstyle.model.repository.AccountRepository
import com.lzm.wanandroidwithlittleblackboxstyle.model.bean.ArticleItem
import com.lzm.wanandroidwithlittleblackboxstyle.model.bean.ArticlesData
import com.lzm.wanandroidwithlittleblackboxstyle.model.repository.ArticlesRespository
import org.slf4j.LoggerFactory

class ArticlesViewModel:BaseViewModel() {
    private val logger: org.slf4j.Logger = LoggerFactory.getLogger(ArticlesViewModel::class.java)

    private val articlesRepository by lazy { ArticlesRespository() }
    suspend fun getArticles(page:Int):ArticlesData{
        return articlesRepository.getArticles(page)
    }
}