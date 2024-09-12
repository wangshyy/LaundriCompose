package com.laundri.wsy.bean.request

/**
 *  author : wsy
 *  date   : 2024/9/4
 *  desc   :
 */
class AuthorizedLoginRequest(
    var agent: String = "xlapp",
    var authType: String,
    var clientId: String,
    var code: String,
    var mer: String = "xl"
)