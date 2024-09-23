package com.lzm.wanandroidwithlittleblackboxstyle.viewmodel

import com.lzm.wanandroidwithlittleblackboxstyle.model.bean.ArticlesData
import com.lzm.wanandroidwithlittleblackboxstyle.model.ResponseBean
import com.lzm.wanandroidwithlittleblackboxstyle.model.bean.UserInfo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {


    @POST("user/login")
    fun login( @Query("username") username: String,@Query("password") password: String):Call<ResponseBean<UserInfo>>


    @GET("user/logout/json")
    fun logout():Call<ResponseBean<String>>

    @GET("article/list/{page}/json")
    fun getArticles(@Path("page") page: Int): Call<ResponseBean<ArticlesData>>
}