package com.lzm.wanandroidwithlittleblackboxstyle.viewmodel

import com.lzm.wanandroidwithlittleblackboxstyle.model.bean.ArticlesData
import com.lzm.wanandroidwithlittleblackboxstyle.model.ResponseBean
import com.lzm.wanandroidwithlittleblackboxstyle.model.bean.UserInfo
import com.lzm.wanandroidwithlittleblackboxstyle.utils.RetrofitUtils
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
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

    @POST("lg/collect/{id}/json")
    fun collectInnerArticle(@Path("id") id: Int, @Header("Cookie") cookie1: String,
                            @Header("Cookie") cookie2: String): Call<ResponseBean<String>>


    @POST("lg/uncollect_originId/{id}/json")
    fun uncollectInnerArticle(@Path("id") id: Int, @Header("Cookie") cookie1: String,
                              @Header("Cookie") cookie2: String): Call<ResponseBean<String>>


    @POST("lg/collect/add/json")
    fun collectOuterArticle(
        @Query("title") title: String,
        @Query("author") author: String,
        @Query("link") link: String
    ): Call<ResponseBean<String>>
}