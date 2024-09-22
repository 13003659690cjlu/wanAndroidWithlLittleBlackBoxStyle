package com.lzm.wanandroidwithlittleblackboxstyle.viewmodel

import com.lzm.wanandroidwithlittleblackboxstyle.model.LoginRequest
import com.lzm.wanandroidwithlittleblackboxstyle.model.ResponseBean
import com.lzm.wanandroidwithlittleblackboxstyle.model.UserInfo
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {


    @POST("user/login")
    fun login( @Query("username") username: String,@Query("password") password: String):Call<ResponseBean<UserInfo>>


    @GET("user/logout/json")
    fun logout():Call<ResponseBean<String>>
}