package com.wsy.laundricompose.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import com.wsy.laundricompose.R
import com.wsy.laundricompose.data.DeviceBean
import com.wsy.laundricompose.ui.theme.ColorOrangeFF7906
import com.wsy.laundricompose.ui.theme.ColorTextDefault
import com.wsy.laundricompose.ui.theme.ColorTextFF343A40
import com.wsy.laundricompose.ui.theme.ColorTextHint
import com.wsy.laundricompose.ui.theme.ColorWhiteAEB5BC
import com.wsy.laundricompose.ui.theme.ColorWhiteF2F2F7
import com.wsy.laundricompose.ui.theme.Primary

/**
 *  author : wsy
 *  date   : 2024/2/23
 *  desc   :
 */

@Composable
fun HomeScreen(screenNavController: NavHostController) {
    val viewModel: HomeViewModel = viewModel()
    return Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 20.dp, top = 15.dp, end = 20.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "苏州科技大学",
            color = ColorTextHint,
            fontSize = 12.sp,
            fontWeight = FontWeight.W400
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "5楼洗衣房",
                color = ColorTextDefault,
                fontSize = 20.sp,
                fontWeight = FontWeight.W700
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "换门店",
                    color = ColorTextHint,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.W400
                )
                Icon(
                    painter = painterResource(R.drawable.icon_arrow_down),
                    contentDescription = ""
                )
            }
        }
        Row(
            modifier = Modifier
                .padding(top = 5.dp),
        ) {
            Text(
                text = "营业时间",
                color = ColorTextDefault,
                fontSize = 12.sp,
                fontWeight = FontWeight.W400
            )
            Text(
                text = "10:00  No.1",
                color = ColorTextFF343A40,
                fontSize = 12.sp,
                fontWeight = FontWeight.W400
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
                .background(color = ColorWhiteF2F2F7, shape = RoundedCornerShape(10.dp))
                .padding(horizontal = 15.dp, vertical = 12.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    modifier = Modifier.weight(2f),
                    textAlign = TextAlign.Start,
                    text = "设备",
                    color = ColorWhiteAEB5BC,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.W400
                )
                Text(
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    text = "总台数",
                    color = ColorWhiteAEB5BC,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.W400
                )
                Text(
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    text = "空闲数",
                    color = ColorWhiteAEB5BC,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.W400
                )
            }
            viewModel.deviceList.forEach {
                BuildDeviceItemView(it)
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentAlignment = Alignment.BottomCenter
        ) {
            Surface(
                modifier = Modifier
                    .padding(bottom = 30.dp)
                    .clickable {
                        screenNavController.navigate("smartBooking")
                    },
                shape = RoundedCornerShape(500.dp),
                color = Primary,
                contentColor = Color.White,
                shadowElevation = 8.dp,
            ) {
                Row(
                    modifier = Modifier
                        .padding(0.dp)
                        .padding(18.dp),
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.icon_booking_button),
                        contentDescription = "预约图标"
                    )
                    Text(
                        modifier = Modifier
                            .padding(start = 10.dp),
                        text = stringResource(id = R.string.smartBooking),
                        color = Color.White,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        modifier = Modifier
                            .padding(start = 8.dp),
                        text = stringResource(id = R.string.washDryEquipment),
                        color = Color.White,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.W400
                    )
                }
            }
        }
    }
}

@Composable
fun BuildDeviceItemView(device: DeviceBean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Row(
            modifier = Modifier.weight(2f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .width(2.dp)
                    .height(14.dp)
                    .background(color = if (device.deviceType == "1") ColorOrangeFF7906 else Primary)
            )
            Text(
                modifier = Modifier.padding(start = 10.dp),
                text = device.deviceName,
                color = ColorTextDefault,
                fontSize = 14.sp,
                fontWeight = FontWeight.W400
            )
        }
        Text(
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center,
            text = "${device.totalNumber}",
            color = ColorTextDefault,
            fontSize = 14.sp,
            fontWeight = FontWeight.W400
        )
        Text(
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center,
            text = "${device.idleNumber}",
            color = ColorTextDefault,
            fontSize = 14.sp,
            fontWeight = FontWeight.W400
        )
    }
}