package com.keyvalue.workshop.ottworkshopapp.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.keyvalue.workshop.ottworkshopapp.TAG
import com.keyvalue.workshop.ottworkshopapp.data.repositoryimpl.MovieRepositoryImpl
import com.keyvalue.workshop.ottworkshopapp.presentation.MovieViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val movieViewModel =  viewModel<MovieViewModel>()
            movieViewModel.selectedMovie.observe(this@MainActivity){
                    details->
                Log.d(TAG, details?.backdrop_path.toString())

            }
            movieViewModel.movies.observe(this@MainActivity) { data->

                Log.d(TAG, data?.results?.get(0)?.original_title.toString())
                movieViewModel.getMovieDetail(data?.results?.get(0)?.id!!)

            }
            movieViewModel.getMovies()
            MainContent()
        }
    }
    @Composable
    fun MainContent() {


    Text(text = "Hello")



    }
}


