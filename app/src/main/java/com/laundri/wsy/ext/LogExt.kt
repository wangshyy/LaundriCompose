package com.laundri.wsy.ext

import android.annotation.SuppressLint
import android.os.Process
import android.util.Log
import com.laundri.wsy.BuildConfig
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 *  author : wsy
 *  date   : 2024/06/27
 *  desc   : Log 拓展
 */
val showLog = BuildConfig.DEBUG
private enum class LogLevel {
    V, D, I, W, E
}

fun String.logV(tag: String, isLog2File: Boolean = false) = log(LogLevel.V, tag, this, isLog2File)
fun String.logD(tag: String, isLog2File: Boolean = false) = log(LogLevel.D, tag, this, isLog2File)
fun String.logI(tag: String, isLog2File: Boolean = false) = log(LogLevel.I, tag, this, isLog2File)
fun String.logW(tag: String, isLog2File: Boolean = false) = log(LogLevel.W, tag, this, isLog2File)
fun String.logE(tag: String, isLog2File: Boolean = false) = log(LogLevel.E, tag, this, isLog2File)

private fun log(level: LogLevel, tag: String, message: String, isLog2File: Boolean) {
    if (isLog2File) {
        log2File(tag,message)
    }
    if (!showLog) return
    when (level) {
        LogLevel.V -> Log.v(tag, message)
        LogLevel.D -> Log.d(tag, message)
        LogLevel.I -> Log.i(tag, message)
        LogLevel.W -> Log.w(tag, message)
        LogLevel.E -> Log.e(tag, message)
    }
}

@SuppressLint("SdCardPath")
private fun log2File(tag: String, message: String) {
    val file = File("/sdcard/log.txt")
    // 获取日期时间
    val date = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
    // 获取当前进程ID
    val pid = Process.myPid()
    // 获取当前线程ID
    val tid = Process.myTid()

    try {
        // 未创建
        if (!file.exists()) {
            file.createNewFile()
        }
        val buf = BufferedWriter(FileWriter(file,true))
        buf.apply {
            // 日志输出格式：2023-11-07 16:43:52 20612-20612 MainActivity 用户点击获取按钮
            append("$date $pid-$tid $tag $message")
            newLine()
            close()
        }
    } catch (e: IOException) {
        e.printStackTrace()
    }
}