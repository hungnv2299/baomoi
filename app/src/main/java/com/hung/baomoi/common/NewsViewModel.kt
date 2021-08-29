package com.hung.baomoi.common

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.*
import android.net.NetworkCapabilities.*
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.hung.baomoi.NewsApplication
import com.hung.baomoi.common.ultis.Constants.Companion.CONVERTION_ERROR
import com.hung.baomoi.common.ultis.Constants.Companion.NETWORK_FAILTURE
import com.hung.baomoi.common.ultis.Constants.Companion.NO_INTERNET_CONNECTION
import com.hung.baomoi.news.data.models.Article
import com.hung.baomoi.news.data.models.NewsResponse
import com.hung.baomoi.news.repository.NewsRepository
import com.hung.baomoi.news.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

class NewsViewModel(
    app: Application,
    val newsRepository: NewsRepository
) : AndroidViewModel(app) {

    val breakingNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var breakingNewsPage = 1
    var breakingNewsResponse: NewsResponse? = null

    val searchNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var searchNewsPage = 1
    var searchNewsResponse: NewsResponse? = null

    //ViewPAGER

    val sportNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var sportNewsResponse: NewsResponse? = null
    var sportNewsPage = 1

    val covidNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var covidNewsResponse: NewsResponse? = null
    var covidNewsPage = 1

    val showbizNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var showbizNewsResponse: NewsResponse? = null
    var showbizNewsPage = 1

    val lawNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var lawNewsResponse: NewsResponse? = null
    var lawNewsPage = 1

    val carNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var carNewsResponse: NewsResponse? = null
    var carNewsPage = 1

    val techNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var techNewsResponse: NewsResponse? = null
    var techNewsPage = 1

    val foodNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var foodNewsResponse: NewsResponse? = null
    var foodNewsPage = 1


    init {
        getBreakingNews("us")
        getSportNews()
        getCovidNews()
        getShowbizNews()
        getLawNews()
        getCarNews()
        getTechNews()
        getFoodNews()
    }

    fun getSportNews() = viewModelScope.launch {
        sportNews.postValue(Resource.Loading())
        val response = newsRepository.searchNews("sport", sportNewsPage)
        sportNews.postValue(handleSportNews(response))
    }

    fun getCovidNews() = viewModelScope.launch {
        covidNews.postValue(Resource.Loading())
        val response = newsRepository.searchNews("covid", covidNewsPage)
        covidNews.postValue(handleCovidNews(response))
    }

    fun getShowbizNews() = viewModelScope.launch {
        showbizNews.postValue(Resource.Loading())
        val response = newsRepository.searchNews("entertainment", showbizNewsPage)
        showbizNews.postValue(handleShowbizNews(response))
    }

    fun getLawNews() = viewModelScope.launch {
        lawNews.postValue(Resource.Loading())
        val response = newsRepository.searchNews("law", lawNewsPage)
        lawNews.postValue(handleLawNews(response))
    }

    fun getCarNews() = viewModelScope.launch {
        carNews.postValue(Resource.Loading())
        val response = newsRepository.searchNews("car", carNewsPage)
        carNews.postValue(handleCarNews(response))
    }

    fun getTechNews() = viewModelScope.launch {
        techNews.postValue(Resource.Loading())
        val response = newsRepository.searchNews("technology", techNewsPage)
        techNews.postValue(handleTechNews(response))
    }

    fun getFoodNews() = viewModelScope.launch {
        foodNews.postValue(Resource.Loading())
        val response = newsRepository.searchNews("food", foodNewsPage)
        foodNews.postValue(handleFoodNews(response))
    }

    fun getBreakingNews(countryCode: String) = viewModelScope.launch {
        safeBreakingNewsCall(countryCode)
    }

    fun searchNews(searchQuery: String) = viewModelScope.launch {
        safeSearchNewsCall(searchQuery)
    }

    private fun handleBreakingNewsResponse(response: Response<NewsResponse>): Resource<NewsResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                breakingNewsPage++
                if (breakingNewsResponse == null) {
                    breakingNewsResponse = resultResponse
                } else {
                    val oldArticle = breakingNewsResponse?.articles
                    val newArticle = resultResponse.articles
                    oldArticle?.addAll(newArticle)
                }
                return Resource.Success(breakingNewsResponse ?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    private fun handleSearchNewsResponse(response: Response<NewsResponse>): Resource<NewsResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                searchNewsPage++
                searchNewsResponse = resultResponse


                return Resource.Success(searchNewsResponse ?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    private fun handleSportNews(response: Response<NewsResponse>): Resource<NewsResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                sportNewsPage++
                if (sportNewsResponse == null) {
                    sportNewsResponse = resultResponse
                } else {
                    val oldArticles = sportNewsResponse?.articles
                    val newArticles = resultResponse.articles
                    oldArticles?.addAll(newArticles)
                }
                return Resource.Success(sportNewsResponse ?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    private fun handleCovidNews(response: Response<NewsResponse>): Resource<NewsResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                covidNewsPage++
                if (covidNewsResponse == null) {
                    covidNewsResponse = resultResponse
                } else {
                    val oldArticles = covidNewsResponse?.articles
                    val newArticles = resultResponse.articles
                    oldArticles?.addAll(newArticles)
                }
                return Resource.Success(covidNewsResponse ?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    private fun handleShowbizNews(response: Response<NewsResponse>): Resource<NewsResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                showbizNewsPage++
                if (showbizNewsResponse == null) {
                    showbizNewsResponse = resultResponse
                } else {
                    val oldArticles = showbizNewsResponse?.articles
                    val newArticles = resultResponse.articles
                    oldArticles?.addAll(newArticles)
                }
                return Resource.Success(showbizNewsResponse ?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    private fun handleLawNews(response: Response<NewsResponse>): Resource<NewsResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                lawNewsPage++
                if (lawNewsResponse == null) {
                    lawNewsResponse = resultResponse
                } else {
                    val oldArticles = lawNewsResponse?.articles
                    val newArticles = resultResponse.articles
                    oldArticles?.addAll(newArticles)
                }
                return Resource.Success(lawNewsResponse ?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    private fun handleCarNews(response: Response<NewsResponse>): Resource<NewsResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                carNewsPage++
                if (carNewsResponse == null) {
                    carNewsResponse = resultResponse
                } else {
                    val oldArticles = carNewsResponse?.articles
                    val newArticles = resultResponse.articles
                    oldArticles?.addAll(newArticles)
                }
                return Resource.Success(carNewsResponse ?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    private fun handleTechNews(response: Response<NewsResponse>): Resource<NewsResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                techNewsPage++
                if (techNewsResponse == null) {
                    techNewsResponse = resultResponse
                } else {
                    val oldArticles = techNewsResponse?.articles
                    val newArticles = resultResponse.articles
                    oldArticles?.addAll(newArticles)
                }
                return Resource.Success(techNewsResponse ?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    private fun handleFoodNews(response: Response<NewsResponse>): Resource<NewsResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                foodNewsPage++
                if (foodNewsResponse == null) {
                    foodNewsResponse = resultResponse
                } else {
                    val oldArticles = foodNewsResponse?.articles
                    val newArticles = resultResponse.articles
                    oldArticles?.addAll(newArticles)
                }
                return Resource.Success(foodNewsResponse ?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    fun saveArticle(article: Article) = viewModelScope.launch {
        newsRepository.upsert(article)
    }

    fun getSavedNews() = newsRepository.getSavedNew()

    fun deleteArticle(article: Article) = viewModelScope.launch {
        newsRepository.deleteArticle(article)
    }

    private suspend fun safeSearchNewsCall(searchQuery: String) {
        searchNews.postValue(Resource.Loading())
        try {
            if (hasInternetConnection()) {
                val response = newsRepository.searchNews(searchQuery, searchNewsPage)
                searchNews.postValue(handleSearchNewsResponse(response))
            } else {
                searchNews.postValue(Resource.Error(NO_INTERNET_CONNECTION))
            }
        } catch (t: Throwable) {
            when (t) {
                is IOException -> searchNews.postValue(Resource.Error(NETWORK_FAILTURE))
                else -> searchNews.postValue(Resource.Error(CONVERTION_ERROR))
            }
        }
    }

    private suspend fun safeBreakingNewsCall(countryCode: String) {
        breakingNews.postValue(Resource.Loading())
        try {
            if (hasInternetConnection()) {
                val response = newsRepository.getBreakingNews(countryCode, breakingNewsPage)
                breakingNews.postValue(handleBreakingNewsResponse(response))
            } else {
                breakingNews.postValue(Resource.Error(NO_INTERNET_CONNECTION))
            }
        } catch (t: Throwable) {
            when (t) {
                is IOException -> breakingNews.postValue(Resource.Error(NETWORK_FAILTURE))
                else -> breakingNews.postValue(Resource.Error(CONVERTION_ERROR))
            }
        }
    }

    private fun hasInternetConnection(): Boolean {
        val connectivityManager = getApplication<NewsApplication>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val activeNetwork = connectivityManager.activeNetwork ?: return false
            val capabilities =
                connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
            return when {
                capabilities.hasTransport(TRANSPORT_WIFI) -> true
                capabilities.hasTransport(TRANSPORT_CELLULAR) -> true
                capabilities.hasTransport(TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            connectivityManager.activeNetworkInfo?.run {
                return when (type) {
                    TYPE_WIFI -> true
                    TYPE_MOBILE -> true
                    TYPE_ETHERNET -> true
                    else -> false
                }
            }
        }
        return false
    }

}