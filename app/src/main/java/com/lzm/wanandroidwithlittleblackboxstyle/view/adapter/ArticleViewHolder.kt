package com.lzm.wanandroidwithlittleblackboxstyle.view.adapter

import android.annotation.SuppressLint
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lzm.wanandroidwithlittleblackboxstyle.R
import com.lzm.wanandroidwithlittleblackboxstyle.model.bean.ArticleItem
import com.lzm.wanandroidwithlittleblackboxstyle.utils.TimeUtil

class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val title: TextView = itemView.findViewById(R.id.articletitle)
    private val source: TextView = itemView.findViewById(R.id.source)
    private val author: TextView = itemView.findViewById(R.id.articleAuthor)
//    private val articleCategory: TextView = itemView.findViewById(R.id.category)
    private val publishTime: TextView = itemView.findViewById(R.id.publishTime)

    @SuppressLint("SetTextI18n")
    fun bind(article: ArticleItem) {
        title.text=article.title
        source.text=article.superChapterName
        author.text=article.author.takeIf { it.isNotBlank() }?:"分享"
//        articleCategory.text="分类: ${article.tags.get(0)}/${article.tags.get(1)}"
        publishTime.text=TimeUtil.millisToDateTime(article.publishTime)
    }
}