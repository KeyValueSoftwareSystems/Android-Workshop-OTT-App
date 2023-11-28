package com.keyvalue.workshop.ottworkshopapp.activity

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.keyvalue.workshop.ottworkshopapp.TAG
import com.keyvalue.workshop.ottworkshopapp.domain.model.Result
import com.keyvalue.workshop.ottworkshopapp.presentation.MovieViewModel
import com.keyvalue.workshop.ottworkshopapp.receiver.AirplaneModeReceiver
import com.keyvalue.workshop.ottworkshopapp.receiver.TestReceiver
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {

    private val airplaneModeReceiver: AirplaneModeReceiver = AirplaneModeReceiver()
    private val testReceiver: TestReceiver = TestReceiver()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerReceiver(airplaneModeReceiver, IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED))
        registerReceiver(testReceiver, IntentFilter("TEST_ACTION"))

        setContent {
          /*  var movieList: List<Result?>? by remember {
                mutableStateOf(null)
            }*/
            val movieViewModel =  viewModel<MovieViewModel>()
            movieViewModel.selectedMovie.observe(this@MainActivity){
                    details->
                Log.d(TAG, details?.backdrop_path.toString())

            }
            movieViewModel.movies.observe(this@MainActivity) { data->

                Log.d(TAG, data?.results?.get(0)?.original_title.toString())
             //   movieList = data?.results
                CoroutineScope(Dispatchers.IO).launch {
                    movieViewModel.getMovieDetail(data?.results?.get(0)?.id!!)

                }

            }
            sendBroadcast(Intent("TEST_ACTION"))

            LaunchedEffect(key1 = 12, block = {
                CoroutineScope(Dispatchers.IO).launch {
                    movieViewModel.getMovies()

                }
            })

            MainContent()

           /* LazyColumn(


                // content padding
                contentPadding = PaddingValues(
                    start = 12.dp,
                    top = 16.dp,
                    end = 12.dp,
                    bottom = 16.dp
                ),
                content = {
                    movieList?.size?.let {
                        items(it) { index ->
                            Card(
                                colors = CardDefaults.cardColors(
                                    containerColor = MaterialTheme.colorScheme.primary,
                                ),
                                modifier = Modifier
                                    .padding(4.dp)
                                    .fillMaxWidth(),
                                elevation =CardDefaults.cardElevation(
                                    defaultElevation = 6.dp
                                ),
                            ) {
                                Text(
                                    text = movieList!![index]?.original_title?:"N/A",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 30.sp,
                                    color = Color(0xFFFFFFFF),
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.padding(16.dp)
                                )
                            }
                        }
                    }
                }
            )
            Button(onClick = { movieList = movieList?.subList(0,2) }) {
                Text(text = "Update")

            }*/
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(airplaneModeReceiver)
        unregisterReceiver(testReceiver)
    }
    @Composable
    fun MainContent() {

       Text(text = "Hello")






    }
}


