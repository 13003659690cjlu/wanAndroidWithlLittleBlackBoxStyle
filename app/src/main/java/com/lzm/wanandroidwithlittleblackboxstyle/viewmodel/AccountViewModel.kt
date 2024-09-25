package com.lzm.wanandroidwithlittleblackboxstyle.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.lzm.wanandroidwithlittleblackboxstyle.model.ResponseBean
import com.lzm.wanandroidwithlittleblackboxstyle.model.bean.LoginInfo
import com.lzm.wanandroidwithlittleblackboxstyle.model.bean.UserInfo
import com.lzm.wanandroidwithlittleblackboxstyle.model.repository.AccountRepository
import com.lzm.wanandroidwithlittleblackboxstyle.utils.RetrofitUtils
import kotlinx.coroutines.launch
import org.slf4j.LoggerFactory

class AccountViewModel: BaseViewModel() {
    private val logger: org.slf4j.Logger = LoggerFactory.getLogger(AccountViewModel::class.java)
    private val _loginInfo = MutableLiveData<ResponseBean<UserInfo>>()
    val loginInfo: LiveData<ResponseBean<UserInfo>> get() = _loginInfo



    private val accountRepository by lazy { AccountRepository() }
    fun login(usernama:String,password:String){
        viewModelScope.launch {
            accountRepository.getLoginData(usernama,password){
                result ->
                result.onSuccess { loginData ->
                    if(loginData.errorCode==0){
                        RetrofitUtils.refreshAccount(usernama,password)
                    }
                    _loginInfo.postValue(loginData)
                }
                result.onFailure { exception ->
                    logger.info("${exception.message}")
                }
            }
        }
    }

    suspend fun logout():String{
        val logoutData = accountRepository.logout()
        return logoutData
    }
}