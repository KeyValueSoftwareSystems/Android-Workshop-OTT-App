package com.keyvalue.workshop.ottworkshopapp.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.keyvalue.workshop.ottworkshopapp.data.repositoryimpl.MovieRepositoryImpl
import com.keyvalue.workshop.ottworkshopapp.presentation.MovieViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainContent()
        }
    }
}


@Composable
fun MainContent() {
    val viewModel =  viewModel<MovieViewModel>()
    val moviesState = viewModel.movies
    val selectedMovieState = viewModel.selectedMovie



    Text(text = "$moviesState")
}