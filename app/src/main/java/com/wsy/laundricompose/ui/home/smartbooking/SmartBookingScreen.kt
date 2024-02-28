package com.wsy.laundricompose.ui.home.smartbooking

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.wsy.laundricompose.R
import com.wsy.laundricompose.ui.Screen

/**
 *  author : wsy
 *  date   : 2024/2/27
 *  desc   :
 */
@Composable
fun SmartBookingScreen() {
    Screen(title = stringResource(id = R.string.smartBooking)) {
        Column(modifier = Modifier.padding(it)) {

        }
    }
}