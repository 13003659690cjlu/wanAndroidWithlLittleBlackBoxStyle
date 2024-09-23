package com.lzm.wanandroidwithlittleblackboxstyle.model.bean

data class ArticlesData(
    val curPage: Int,
    val datas: List<ArticleItem>,
    val offset: Int,
    val over: Boolean,
    val pageCount: Int,
    val size: Int,
    val total: Int
)


