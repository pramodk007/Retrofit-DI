package com.androiddev.retrofit_di.repository

import com.androiddev.retrofit_di.data.SearchResultModel
import com.androiddev.retrofit_di.network.SongServiceApi
import retrofit2.Response
import javax.inject.Inject

class SongRepository @Inject constructor(
    private val api: SongServiceApi
){
    suspend fun getSong(key:String) : Response<SearchResultModel> {
        return api.getSearchResults(key)
    }
}