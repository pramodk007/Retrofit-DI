package com.androiddev.retrofit_di.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.androiddev.retrofit_di.repository.SongRepository
import javax.inject.Inject

class MainViewModelFactory @Inject constructor(private val repository: SongRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }

}