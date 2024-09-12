package com.laundri.wsy.bean

/**
 *  author : wsy
 *  date   : 2024/9/4
 *  desc   :
 */
data class User(
    var alipayUserid: String?,
    var appUserId: String?,
    var cardLatestDueTime: Long?,
    var ciId: Long?,
    var createTime: Long?,
    var disableReason: String?,
    var disableTime: Long?,
    var firstCardTime: Long?,
    var firstOrderTime: Long?,
    var gender: String?,
    var isHaveGoldCoin: Int?,
    var lastAppId: String?,
    var nickName: String?,
    var openid: String?,
    var phoneNumber: String?,
    var physiologicalGender: String?,
    var picture: String?,
    var remark: String?,
    var sub: String?,
    var unionId: String?,
    var userCode: Long?,
    var userId: String?,
    var yunMaUserId: String?
)