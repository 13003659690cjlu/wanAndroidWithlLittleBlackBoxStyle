package com.lzm.wanandroidwithlittleblackboxstyle.utils

import com.lzm.wanandroidwithlittleblackboxstyle.viewmodel.ApiService
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitUtils {
    private const val BASE_URL="https://www.wanandroid.com/"

     var username: String = ""
     var password: String = ""
//    private val loginFlow: MutableStateFlow<String> = MutableStateFlow("")
//
//    init {
//        GlobalScope.launch {
//            loginFlow.collect {
//                // 更新 token 的值
//            }
//        }
//    }
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getHttpService():ApiService{
        return retrofit.create(ApiService::class.java)
    }

    fun refreshAccount(username:String,password:String){
        this.username=username
        this.password=password
    }


    fun logoutAccount(){
        this.username=""
        this.password=""
    }

}