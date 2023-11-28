package com.keyvalue.workshop.ottworkshopapp.data.repositoryimpl

import RetrofitHelper
import android.util.Log
import com.keyvalue.workshop.ottworkshopapp.TAG
import com.keyvalue.workshop.ottworkshopapp.data.remote.MovieApi
import com.keyvalue.workshop.ottworkshopapp.domain.model.Movie
import com.keyvalue.workshop.ottworkshopapp.domain.model.MovieDetail
import com.keyvalue.workshop.ottworkshopapp.domain.repository.MovieRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response

class MovieRepositoryImpl(): MovieRepository {
    private val movieApi: MovieApi = RetrofitHelper.getInstance().create(MovieApi::class.java)


    private val token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI2YmRmNGJlNTNkM2Y0YWU0MGNjMjVmYzMxYWI4MTkwNiIsInN1YiI6IjY1NWM4Njk2ZWE4NGM3MTA5NWEwYmIwMCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.3Yc8hwk0Bn5_fJzTZIfjyTQ69mxOXeNruRjv-Dza9Ws"



    suspend fun getMovies(callback: suspend (Movie?) -> Unit) {
        try {
            val resp =  movieApi.getPopularMovies("en-US",1,token)
            if(resp.isSuccessful)
            {
                callback(resp.body())

            }
            else
            {
                callback(null)
            }
        }catch (e:HttpException)
        {
            Log.d(TAG,"Code: "+e.code() + "Message: "+e.message())
            callback(null)

        }
      /*  movieApi.getPopularMovies("en-US",1,token).enqueue(object : Callback<Movie> {

            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                if (response.isSuccessful) {
                    CoroutineScope(Dispatchers.IO).launch {
                        callback(response.body())
                    }

                }
                else{
                    CoroutineScope(Dispatchers.IO).launch {
                        callback(null)

                    }
                }
            }

            override fun onFailure(call: Call<Movie>, t: Throwable) {
                CoroutineScope(Dispatchers.IO).launch {
                    callback(null)

                }
            }
        })*/
    }

    suspend fun getMovieDetail(movieId: Int, callback: suspend (MovieDetail?) -> Unit) {
        try {
            val resp =  movieApi.getMovieDetail(movieId, "en-US",token)
            if(resp.isSuccessful)
            {
                callback(resp.body())

            }
            else
            {
                callback(null)
            }
        }catch (e:HttpException)
        {
            Log.d(TAG,"Code: "+e.code() + "Message: "+e.message())
            callback(null)

        }

      /*  movieApi.getMovieDetail(movieId, "en-US",token).enqueue(object : Callback<MovieDetail> {
            override fun onResponse(call: Call<MovieDetail>, response: Response<MovieDetail>) {
                if (response.isSuccessful) {
                    CoroutineScope(Dispatchers.IO).launch {

                        callback(response.body())
                    }
                } else {
                    CoroutineScope(Dispatchers.IO).launch {

                        callback(null)
                    }
                }
            }

            override fun onFailure(call: Call<MovieDetail>, t: Throwable) {
                CoroutineScope(Dispatchers.IO).launch {
                    callback(null)
                }
            }
        })*/
    }
}