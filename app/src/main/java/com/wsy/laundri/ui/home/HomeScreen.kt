package com.wsy.laundri.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.wsy.laundri.R
import com.wsy.laundri.ui.theme.ColorTextDefault
import com.wsy.laundri.ui.theme.ColorTextFF343A40
import com.wsy.laundri.ui.theme.ColorTextHint

/**
 *  author : wsy
 *  date   : 2024/2/23
 *  desc   :
 */

@Composable
fun HomeScreen(viewModel: HomeViewModel = viewModel()) {
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
    }
}