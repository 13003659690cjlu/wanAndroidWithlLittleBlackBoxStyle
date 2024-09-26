package com.lzm.wanandroidwithlittleblackboxstyle.model.repository

import com.lzm.wanandroidwithlittleblackboxstyle.model.ResponseBean
import com.lzm.wanandroidwithlittleblackboxstyle.model.bean.UserInfo
import com.lzm.wanandroidwithlittleblackboxstyle.utils.RetrofitUtils
import org.slf4j.LoggerFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AccountRepository {
    private val logger = LoggerFactory.getLogger(AccountRepository::class.java)

    fun getLoginData(username: String, password: String, callback: (Result<ResponseBean<UserInfo>>) -> Unit) {
        val httpService = RetrofitUtils.getHttpService()
        val loginCall = httpService.login(username, password)

        loginCall.enqueue(object : Callback<ResponseBean<UserInfo>> {
            override fun onResponse(call: Call<ResponseBean<UserInfo>>, response: Response<ResponseBean<UserInfo>>) {
                if (response.isSuccessful) {
                    logger.info(response.body().toString())
                    callback(Result.success(response.body() as ResponseBean<UserInfo>))
                }
                else{
                    callback(Result.failure(Exception("Failed to fetch data")))
                }
            }

            override fun onFailure(call: Call<ResponseBean<UserInfo>>, t: Throwable) {
                logger.info("登录请求失败")
                t.printStackTrace()
            }
        })
    }

    fun logout(callback: (Result<ResponseBean<String>>) -> Unit) {
        val httpService = RetrofitUtils.getHttpService()
        val logoutCall = httpService.logout()

        logoutCall.enqueue(object : Callback<ResponseBean<String>> {
            override fun onResponse(call: Call<ResponseBean<String>>, response: Response<ResponseBean<String>>) {
                if (response.isSuccessful) {
                    logger.info(response.body().toString())
                    callback(Result.success(response.body() as ResponseBean<String>))
                }
                else{
                    callback(Result.failure(Exception("Failed to fetch data")))
                }
            }

            override fun onFailure(call: Call<ResponseBean<String>>, t: Throwable) {
                logger.info("登出失败")
                t.printStackTrace()
            }
        })
    }


}