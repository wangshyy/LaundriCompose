package com.wsy.laundricompose.data

/**
 *  author : wsy
 *  date   : 2024/2/23
 *  desc   :
 */
class DeviceBean(
    var id: Int,
    var deviceName: String,
    // 0-> 洗衣机 1->烘干机
    var deviceType: String,
    var totalNumber: Int,
    var idleNumber: Int
)