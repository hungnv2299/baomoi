package com.hung.baomoi

import android.os.Bundle
import android.provider.MediaStore
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.hung.baomoi.common.NewsViewModel
import com.hung.baomoi.common.NewsViewModelProviderFactory
import com.hung.baomoi.common.VideoViewModelProviderFactory
import com.hung.baomoi.news.data.local.ArticleDatabase
import com.hung.baomoi.news.repository.NewsRepository
import com.hung.baomoi.video.VideoViewModel
import com.hung.baomoi.video.data.local.VideosDatabase
import com.hung.baomoi.video.repository.VideoRepository
import kotlinx.android.synthetic.main.activity_news.*

class NewsActivity : AppCompatActivity() {

    lateinit var viewModel: NewsViewModel
    lateinit var videoViewModel: VideoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        val newsRepository = NewsRepository(ArticleDatabase(this))
        val viewModelProviderFactory = NewsViewModelProviderFactory(application, newsRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(NewsViewModel::class.java)
        bottomNavigationView.setupWithNavController(newsNavHostFragment.findNavController())

        val videoRepository = VideoRepository(VideosDatabase(this))
        val videoViewModelProviderFactory = VideoViewModelProviderFactory(videoRepository)
        videoViewModel = ViewModelProvider(this, videoViewModelProviderFactory).get(VideoViewModel::class.java)
    }
}