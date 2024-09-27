package com.lzm.wanandroidwithlittleblackboxstyle.model.repository

import com.lzm.wanandroidwithlittleblackboxstyle.model.ResponseBean
import com.lzm.wanandroidwithlittleblackboxstyle.model.bean.ArticlesData
import com.lzm.wanandroidwithlittleblackboxstyle.model.bean.UserInfo
import com.lzm.wanandroidwithlittleblackboxstyle.utils.RetrofitUtils
import org.slf4j.LoggerFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class ArticlesRespository {
    private val logger = LoggerFactory.getLogger(ArticlesRespository::class.java)

    fun getArticles(page:Int, callback: (Result<ResponseBean<ArticlesData>>) -> Unit) {
        val httpService = RetrofitUtils.getHttpService()
        val articlesCall = httpService.getArticles(page)
        articlesCall.enqueue(object : Callback<ResponseBean<ArticlesData>> {
            override fun onResponse(call: Call<ResponseBean<ArticlesData>>, response: Response<ResponseBean<ArticlesData>>) {
                if (response.isSuccessful) {
                    logger.info(response.body().toString())
                    callback(Result.success(response.body() as ResponseBean<ArticlesData>))
                }
                else{
                    callback(Result.failure(Exception("Failed to fetch data")))
                }
            }

            override fun onFailure(call: Call<ResponseBean<ArticlesData>>, t: Throwable) {
                logger.info("登录请求失败")
                t.printStackTrace()
            }
        })
    }



}
