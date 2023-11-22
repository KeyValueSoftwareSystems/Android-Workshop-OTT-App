package com.keyvalue.workshop.ottworkshopapp.activity

import android.content.Intent
import android.content.IntentFilter
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
import com.keyvalue.workshop.ottworkshopapp.receiver.AirplaneModeReceiver
import com.keyvalue.workshop.ottworkshopapp.receiver.TestReceiver


class MainActivity : ComponentActivity() {

    private val airplaneModeReceiver: AirplaneModeReceiver = AirplaneModeReceiver()
    private val testReceiver: TestReceiver = TestReceiver()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerReceiver(airplaneModeReceiver, IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED))
        registerReceiver(testReceiver, IntentFilter("TEST_ACTION"))

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
            sendBroadcast(Intent("TEST_ACTION"))
            movieViewModel.getMovies()
            MainContent()
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


