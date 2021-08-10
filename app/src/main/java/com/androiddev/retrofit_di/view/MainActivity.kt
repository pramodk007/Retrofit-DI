package com.androiddev.retrofit_di.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.androiddev.retrofit_di.Adapter.SongAdapter
import com.androiddev.retrofit_di.R
import com.androiddev.retrofit_di.data.ResultModel
import com.androiddev.retrofit_di.databinding.ActivityMainBinding
import com.androiddev.retrofit_di.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var songAdapter: SongAdapter
    lateinit var binding: ActivityMainBinding
    var search:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        songAdapter = SongAdapter()
        binding.rvSongsList.setAdapter(songAdapter)
        binding.rvSongsList.layoutManager = GridLayoutManager(this, 2)

        binding.btnSearch.setOnClickListener{
            getSongs();
        }

    }
    private fun getSongs() {
        search = binding.edtSearchSong.text.toString()
        if (search.isEmpty()) {
            Toast.makeText(applicationContext, "enter the artist/song name", Toast.LENGTH_SHORT)
                .show()
        }
        val ch = '+'
        search = search.replace(' ', ch)
        viewModel.getSong(search)
        viewModel.mResponse.observe(this, Observer {
                response->
            if(response.isSuccessful) {
                val resultModelList: List<ResultModel> = response.body()!!.resultModels
                if (resultModelList.isNotEmpty()) {
                    songAdapter.submitList(resultModelList)
                }
            }
            else{
                Log.d("error","cant get song")
            }

        })
    }
}