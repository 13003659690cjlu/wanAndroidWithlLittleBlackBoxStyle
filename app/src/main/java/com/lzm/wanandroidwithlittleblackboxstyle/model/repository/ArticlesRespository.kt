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


    suspend fun getArticles(page:Int): ArticlesData {
        return suspendCoroutine { continuation ->
            val httpService = RetrofitUtils.getHttpService()
            val getArticlesCall = httpService.getArticles(page)
            var articles:ArticlesData?=null
            getArticlesCall.enqueue(object : Callback<ResponseBean<ArticlesData>> {
                override fun onResponse(call: Call<ResponseBean<ArticlesData>>, response: Response<ResponseBean<ArticlesData>>) {
                    if (response.isSuccessful) {
                        logger.info(response.body().toString())
                        val responseBean: ResponseBean<ArticlesData>? = response.body()
                        if (responseBean?.errorCode == 0) {
                            logger.info("获取文章成功")
                            articles= responseBean.data
                        } else {
                            logger.info("获取文章失败")
                        }
                        continuation.resume(articles)
                    }
                }

                override fun onFailure(call: Call<ResponseBean<ArticlesData>>, t: Throwable) {
                    logger.info("登录请求失败")
                    t.printStackTrace()
                    continuation.resumeWithException(t)
                }
            })
        }
    }
}

private fun Any.resume(value: ArticlesData?) {

}
