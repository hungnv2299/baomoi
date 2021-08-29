package com.hung.baomoi.video

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hung.baomoi.video.data.model.VideoResponse
import com.hung.baomoi.video.repository.VideoRepository
import com.hung.baomoi.video.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class VideoViewModel(
    val videoRepository: VideoRepository
) : ViewModel() {
    val videos: MutableLiveData<Resource<VideoResponse>> = MutableLiveData()

    init {
        getVideo()
    }

    fun getVideo() = viewModelScope.launch {
        videos.postValue(Resource.Loading())
        val response = videoRepository.getVideo()
        videos.postValue(handleVideoResponse(response))
    }

    private fun handleVideoResponse(response: Response<VideoResponse>): Resource<VideoResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)

            }

        }
        return Resource.Error(response.message())
    }
}