package com.keyvalue.workshop.ottworkshopapp.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.keyvalue.workshop.ottworkshopapp.TAG
import com.keyvalue.workshop.ottworkshopapp.domain.model.Result
import com.keyvalue.workshop.ottworkshopapp.presentation.MovieViewModel
import androidx.compose.foundation.Image
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import coil.compose.rememberAsyncImagePainter


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val movieViewModel = viewModel<MovieViewModel>()

            // Use remember and mutableStateOf to manage the state of movies
            var movies by remember { mutableStateOf(emptyList<Result>()) }

            LaunchedEffect(movieViewModel) {
                movieViewModel.movies.observe(this@MainActivity) { data ->
                    Log.d(TAG, data?.results?.get(0)?.original_title.toString())
                    movieViewModel.getMovieDetail(data?.results?.get(0)?.id!!)
                    movies = data.results as List<Result>
                }
            }

            LaunchedEffect(movieViewModel) {
                movieViewModel.getMovies()
            }

            MainContent(movies) { clickedMovie ->
//                movies = movies.subList(0,2)
                // Handle the click event by starting the DetailsActivity
                val intent = Intent(this@MainActivity, DetailsActivity::class.java)
                intent.putExtra("movieId", clickedMovie.id!!)
                startActivity(intent)
            }
        }
    }

    @Composable
    fun MainContent(movies: List<Result>, onItemClick: (Result) -> Unit) {
        LazyColumn {
            items(movies) { movie ->
                MovieListItem(movie = movie) {
                    onItemClick(movie)
                }
            }
        }
    }

    // Composable function for a single movie item
    @Composable
    fun MovieListItem(movie: Result?, onItemClick: () -> Unit) {
        Column(
            modifier = Modifier
                .clickable(onClick = onItemClick)
                .padding(16.dp)
        ) {
            // Display movie information, e.g., title, poster path, etc.
            Text(text = movie?.title ?: "", fontWeight = FontWeight.Bold)
            Image(
                painter = rememberAsyncImagePainter("https://image.tmdb.org/t/p/w500${movie?.poster_path}"),
                contentDescription = null, // Provide a meaningful description
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(vertical = 8.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
        }
    }
}



