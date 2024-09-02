package com.laundri.wsy.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 *  author : wsy
 *  date   : 2024/6/28
 *  desc   : 欢迎页
 */
@AndroidEntryPoint
class SplashActivity : ComponentActivity() {
    private val viewModel: SplashViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
        }

        lifecycleScope.launch {
            delay(2 * 1000)
            val intent = Intent(this@SplashActivity, MainActivity::class.java)
            intent.putExtra("isLogin", viewModel.isLogin)
            startActivity(intent)
            finish()
        }
    }
}