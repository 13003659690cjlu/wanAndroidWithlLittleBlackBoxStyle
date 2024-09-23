package com.lzm.wanandroidwithlittleblackboxstyle.model.repository

import com.lzm.wanandroidwithlittleblackboxstyle.model.ResponseBean
import com.lzm.wanandroidwithlittleblackboxstyle.model.bean.UserInfo
import com.lzm.wanandroidwithlittleblackboxstyle.utils.RetrofitUtils
import org.slf4j.LoggerFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class AccountRepository {
    private val logger = LoggerFactory.getLogger(AccountRepository::class.java)

    suspend fun getLoginData(username: String, password: String): String {
        return suspendCoroutine { continuation ->
            val httpService = RetrofitUtils.getHttpService()
            val loginCall = httpService.login(username, password)
            var loginMsg = "登录成功"

            loginCall.enqueue(object : Callback<ResponseBean<UserInfo>> {
                override fun onResponse(call: Call<ResponseBean<UserInfo>>, response: Response<ResponseBean<UserInfo>>) {
                    if (response.isSuccessful) {
                        logger.info(response.body().toString())
                        val responseBean: ResponseBean<UserInfo>? = response.body()
                        if (responseBean?.errorCode == 0) {
                            logger.info("登录成功")
                            loginMsg = "登录成功"
                            RetrofitUtils.refreshAccount(username,password)
                        } else {
                            logger.info("登录失败")
                            val errorMsgRegex = "errorMsg=([^！]+)！".toRegex()
                            val matchResult = errorMsgRegex.find(response.body().toString())
                            loginMsg = matchResult?.groupValues?.getOrNull(1).toString()
                        }
                        continuation.resume(loginMsg)
                    }
                }

                override fun onFailure(call: Call<ResponseBean<UserInfo>>, t: Throwable) {
                    logger.info("登录请求失败")
                    t.printStackTrace()
                    continuation.resumeWithException(t)
                }
            })
        }
    }

    suspend fun logout(): String {
        return suspendCoroutine { continuation ->
            val httpService = RetrofitUtils.getHttpService()
            val logoutCall = httpService.logout()
            var logoutMsg = "登出成功"

            logoutCall.enqueue(object : Callback<ResponseBean<String>> {
                override fun onResponse(call: Call<ResponseBean<String>>, response: Response<ResponseBean<String>>) {
                    if (response.isSuccessful) {
                        logger.info(response.body().toString())
                        val responseBean: ResponseBean<String>? = response.body()
                        if (responseBean?.errorCode == 0) {
                            logger.info("登出成功")
                            logoutMsg = "登出成功"
                            RetrofitUtils.refreshAccount("","")
                        } else {
                            logger.info("登出成功")
                            val errorMsgRegex = "errorMsg=([^！]+)！".toRegex()
                            val matchResult = errorMsgRegex.find(response.body().toString())
                            logoutMsg = matchResult?.groupValues?.getOrNull(1).toString()
                        }
                        continuation.resume(logoutMsg)
                    }
                }

                override fun onFailure(call: Call<ResponseBean<String>>, t: Throwable) {
                    logger.info("登出请求失败")
                    t.printStackTrace()
                    continuation.resumeWithException(t)
                }
            })
        }
    }


}