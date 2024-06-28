package com.wsy.laundricompose.http.repository

import com.wsy.laundricompose.http.ApiService
import com.wsy.laundricompose.http.base.BaseRepository
import javax.inject.Inject

/**
 *  author : wsy
 *  date   : 2024/6/27
 *  desc   :
 */
class MainRepository @Inject constructor(private val service: ApiService) : BaseRepository() {

}