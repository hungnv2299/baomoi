package com.hung.baomoi.models

import com.hung.baomoi.models.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)