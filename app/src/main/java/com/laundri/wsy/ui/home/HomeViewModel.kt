package com.laundri.wsy.ui.home

import androidx.lifecycle.ViewModel
import com.laundri.wsy.bean.DeviceBean

/**
 *  author : wsy
 *  date   : 2024/2/23
 *  desc   :
 */
class HomeViewModel : ViewModel() {
    /// 设备列表
    val deviceList = listOf(
        DeviceBean(
            id = 1,
            deviceName = "洗衣机",
            deviceType = "0",
            idleNumber = 1,
            totalNumber = 1
        ),
        DeviceBean(
            id = 2,
            deviceName = "烘干机",
            deviceType = "1",
            idleNumber = 2,
            totalNumber = 3
        )
    )
}