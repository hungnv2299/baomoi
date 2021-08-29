package com.hung.baomoi.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hung.baomoi.video.VideoViewModel
import com.hung.baomoi.video.repository.VideoRepository

class VideoViewModelProviderFactory(
    val videoRepository: VideoRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return VideoViewModel(videoRepository) as T
    }
}