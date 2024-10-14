package com.lzm.wanandroidwithlittleblackboxstyle.model.repository

import com.lzm.wanandroidwithlittleblackboxstyle.utils.RetrofitUtils
import okhttp3.Interceptor
import okhttp3.Response
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

class AddCookieInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        // 获取用户名和密码
        val username = RetrofitUtils.username
        val password = RetrofitUtils.password

        // 编码包含变量的 Cookie 值
//        val encodedUsername = URLEncoder.encode("loginUserName=$username", StandardCharsets.UTF_8.toString())
//        val encodedPassword = URLEncoder.encode("loginUserPassword=$password", StandardCharsets.UTF_8.toString())

        val encodedUsername ="loginUserName=$username"
        val encodedPassword ="loginUserPassword=$password"


        // 构建两个 Cookie 值
        val cookieValue = "Cookie=$${encodedUsername}; Cookie=${encodedPassword}"

        // 添加 Cookie
        val requestWithCookies = originalRequest.newBuilder()
            .header("Cookie", cookieValue)
            .build()

        return chain.proceed(requestWithCookies)
    }
}