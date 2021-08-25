package com.hung.baomoi.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.hung.baomoi.R
import com.hung.baomoi.ui.NewsActivity
import com.hung.baomoi.ui.NewsViewModel

class ArticleFragment : Fragment(R.layout.fragment_article) {

    lateinit var viewModel: NewsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as NewsActivity).viewModel
    }
}