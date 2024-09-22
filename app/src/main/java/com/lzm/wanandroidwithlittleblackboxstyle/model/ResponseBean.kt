package com.lzm.wanandroidwithlittleblackboxstyle.model

data class ResponseBean<T>(
    val data: T?,
    val errorCode: Int,
    val errorMsg: String
)