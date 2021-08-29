package com.hung.baomoi.video.repository

import com.hung.baomoi.video.data.local.VideosDatabase
import com.hung.baomoi.video.data.remote.RetrofitInstance

class VideoRepository(
    private val db: VideosDatabase
) {
    suspend fun getVideo() = RetrofitInstance.api.getVideo()
}