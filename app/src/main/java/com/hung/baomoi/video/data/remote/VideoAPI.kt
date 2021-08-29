package com.hung.baomoi.video.data.remote

import com.hung.baomoi.video.data.model.VideoResponse
import retrofit2.Response
import retrofit2.http.GET

interface VideoAPI {
    @GET("getVideo")
    suspend fun getVideo():Response<VideoResponse>
}