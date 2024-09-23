package com.lzm.wanandroidwithlittleblackboxstyle.viewmodel

import com.lzm.wanandroidwithlittleblackboxstyle.model.repository.AccountRepository
import org.slf4j.LoggerFactory

class AccountViewModel: BaseViewModel() {
    private val logger: org.slf4j.Logger = LoggerFactory.getLogger(AccountViewModel::class.java)
    private val accountRepository by lazy { AccountRepository() }
    suspend fun login(usernama:String,password:String):String{
        val loginData = accountRepository.getLoginData(usernama, password)
        return loginData
    }

    suspend fun logout():String{
        val logoutData = accountRepository.logout()
        return logoutData
    }
}