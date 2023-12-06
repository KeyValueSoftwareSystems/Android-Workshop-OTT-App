package com.keyvalue.workshop.ottworkshopapp.activity


import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.keyvalue.workshop.ottworkshopapp.TAG
import com.keyvalue.workshop.ottworkshopapp.domain.model.Movie
import com.keyvalue.workshop.ottworkshopapp.domain.model.MovieDetail
import com.keyvalue.workshop.ottworkshopapp.domain.model.Result
import com.keyvalue.workshop.ottworkshopapp.presentation.MovieViewModel

class DetailsActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val movieViewModel = viewModel<MovieViewModel>()
            val movieId = intent.getIntExtra("movieId", -1)
            Log.d(TAG,"DetailsActivity-${movieId}")
            var movie: MovieDetail? by remember {
                mutableStateOf(null)
            }
            // Use movieId to fetch detailed information for the movie and display it
            // You might want to use Jetpack Compose for the UI in DetailsActivity as well
            // ...

            LaunchedEffect(movieViewModel) {
                movieViewModel.selectedMovie.observe(this@DetailsActivity) { data ->
                    data.title?.let { Log.d(TAG, it) }
                    movie = data
                }
            }
            movieViewModel.getMovieDetail(movieId)
            movie?.let { MovieContent(it) }
        }
    }


    @Composable
    fun MovieContent(movie: MovieDetail) {
        SingleMovie(movie)
    }


    @Composable
    fun SingleMovie(movie: MovieDetail) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            // Display movie information, e.g., title, poster path, etc.
            Text(text = movie.title ?: "", fontWeight = FontWeight.Bold)
            Image(
                painter = rememberAsyncImagePainter("https://image.tmdb.org/t/p/w500${movie.poster_path}"),
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