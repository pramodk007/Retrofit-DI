package com.androiddev.retrofit_di.network

import com.androiddev.retrofit_di.data.SearchResultModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SongServiceApi {
    companion object{
        const val BASE_URL = "https://itunes.apple.com/"
    }

    @GET("search")
    suspend fun getSearchResults(@Query("term") searchTerm: CharSequence): Response<SearchResultModel>

    @GET("search?term=jack+johnson")
    suspend fun getJack(): Response<SearchResultModel>
}