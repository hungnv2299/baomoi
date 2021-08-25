package com.hung.baomoi.repository

import com.hung.baomoi.api.RetrofitInstance
import com.hung.baomoi.db.ArticleDatabase

class NewsRepository(
    val db: ArticleDatabase
) {
    suspend fun  getBreakingNews(countrycode : String, pagenumber : Int) =
        RetrofitInstance.api.getBreakingNews(countrycode,pagenumber)
}