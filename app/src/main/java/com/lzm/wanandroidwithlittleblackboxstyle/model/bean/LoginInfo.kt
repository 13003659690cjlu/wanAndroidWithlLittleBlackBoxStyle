package com.lzm.wanandroidwithlittleblackboxstyle.model.bean

import kotlin.properties.Delegates

class LoginInfo {
    public var loginStatus by Delegates.notNull<Boolean>()
    public lateinit var loginMsg:String
}