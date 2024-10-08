package com.lzm.wanandroidwithlittleblackboxstyle.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.lzm.wanandroidwithlittleblackboxstyle.MyApplication
import com.lzm.wanandroidwithlittleblackboxstyle.model.repository.AccountRepository
import com.lzm.wanandroidwithlittleblackboxstyle.model.bean.ArticleItem
import com.lzm.wanandroidwithlittleblackboxstyle.model.bean.ArticlesData
import com.lzm.wanandroidwithlittleblackboxstyle.model.repository.ArticlesRespository
import com.lzm.wanandroidwithlittleblackboxstyle.utils.RetrofitUtils
import com.lzm.wanandroidwithlittleblackboxstyle.utils.ToastUtil
import kotlinx.coroutines.launch
import org.slf4j.LoggerFactory

class ArticlesViewModel:BaseViewModel() {
    private val logger: org.slf4j.Logger = LoggerFactory.getLogger(ArticlesViewModel::class.java)
    private val _articlesData = MutableLiveData<List<ArticleItem>>()
    val articlesData:LiveData<List<ArticleItem>> get() = _articlesData
    private val articlesRepository by lazy { ArticlesRespository() }
    private var page=0
    init {
        _articlesData.value = mutableListOf() // 初始化为空的 MutableList
    }

    fun getArticles(){
        viewModelScope.launch {
            articlesRepository.getArticles(page++){
                result ->
                result.onSuccess { articlesDatas ->
                    if(articlesDatas.errorCode==0){
                        logger.info("数据传输成功")
                        _articlesData.value= articlesDatas.data?.datas
                    }else{
                        ToastUtil.showCustomToast(MyApplication.getInstance(),"加载数据失败")
                    }

                }
                result.onFailure { exception ->
                    logger.info("${exception.message}")
                }
            }
        }
    }
}