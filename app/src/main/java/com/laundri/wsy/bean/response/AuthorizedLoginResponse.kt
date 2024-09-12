package com.laundri.wsy.bean.response

import com.laundri.wsy.bean.User

/**
 *  author : wsy
 *  date   : 2024/9/4
 *  desc   :
 */
data class AuthorizedLoginResponse(
    var timeToken: String?,
    var token: String?,
    var user: User?,
    var userId: String?


) {
    override fun toString(): String {
        return "AuthorizedLoginResponse(timeToken=$timeToken, token=$token, user=$user, userId=$userId)"
    }
}