package com.androiddev.retrofit_di.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androiddev.retrofit_di.data.SearchResultModel
import com.androiddev.retrofit_di.repository.SongRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: SongRepository) : ViewModel() {
    val mResponse : MutableLiveData<Response<SearchResultModel>> = MutableLiveData()

    fun getSong(key:String){
        viewModelScope.launch {
            try {
                val response = repository.getSong(key)

                mResponse.value = response
            }
            catch(e: Exception){
                Log.d("error message",e.toString())
            }
        }
    }


}