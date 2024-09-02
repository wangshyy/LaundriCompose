package com.laundri.wsy.wechat

import android.content.Context
import com.tencent.mm.opensdk.modelmsg.SendAuth
import com.tencent.mm.opensdk.openapi.IWXAPI
import com.tencent.mm.opensdk.openapi.WXAPIFactory

/**
 *  author : wsy
 *  date   : 2024/9/2
 *  desc   : 封装微信的功能
 */
class WechatHelper private constructor() {
    companion object {
        const val APP_ID = "wx9505fbc15f48cdb6"
        val instance by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) { WechatHelper() }
    }

    // 通过WXAPIFactory工厂，获取IWXAPI的实例
    lateinit var api: IWXAPI

    fun init(context: Context) {
        // IWXAPI 是第三方app和微信通信的openApi接口
        api = WXAPIFactory.createWXAPI(context, APP_ID, true)
        // 将应用的appId注册到微信
        api.registerApp(APP_ID)
    }

    /**
     * 获取微信授权code
     *
     */
    fun getAuthCode() {
        // 发送授权登录信息，来获取code
        val req: SendAuth.Req = SendAuth.Req()
        // 应用的作用域，获取个人信息
        req.scope = "snsapi_userinfo"
        /**
         * 用于保持请求和回调的状态，授权请求后原样带回给第三方
         * 为了防止csrf攻击（跨站请求伪造攻击），后期改为随机数加session来校验
         */
        req.state = "app_wechat"
        api.sendReq(req)
    }
}