package com.lzm.wanandroidwithlittleblackboxstyle.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lzm.wanandroidwithlittleblackboxstyle.R
import com.lzm.wanandroidwithlittleblackboxstyle.model.bean.ArticleItem
import com.lzm.wanandroidwithlittleblackboxstyle.utils.RetrofitUtils

class ArticleAdapter(private val articles: MutableList<ArticleItem>,private val onItemClick: (position:Int) -> Unit) : RecyclerView.Adapter<ArticleViewHolder>() {

    private var listener: AdapterView.OnItemClickListener? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.article_item, parent, false)
        return ArticleViewHolder(view)
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    fun setOnItemClickListener(listener: AdapterView.OnItemClickListener) {
        this.listener = listener
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = articles[position]
        holder.bind(article)
        holder.collectButton.setOnClickListener {
            onItemClick(position)
            if(RetrofitUtils.username.isNotBlank()){
                article.collect=!article.collect
                if(article.collect){
                    holder.collectButton.setBackgroundResource(R.drawable.collect_touch)
                }else{
                    holder.collectButton.setBackgroundResource(R.drawable.collect_untouch)
                }
            }
        }
    }


}