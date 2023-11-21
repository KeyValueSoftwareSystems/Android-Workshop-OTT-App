package com.keyvalue.workshop.ottworkshopapp.data.repositoryimpl

import com.keyvalue.workshop.ottworkshopapp.data.remote.MovieApi
import com.keyvalue.workshop.ottworkshopapp.domain.model.MovieDetails
import com.keyvalue.workshop.ottworkshopapp.domain.repository.MovieRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepositoryImpl(private val movieApi: MovieApi): MovieRepository {

    fun getMovies(callback: (List<MovieDetails>?) -> Unit) {
        movieApi.getMovies().enqueue(object : Callback<List<MovieDetails>> {
            override fun onResponse(call: Call<List<MovieDetails>>, response: Response<List<MovieDetails>>) {
                if (response.isSuccessful) {
                    callback(response.body())
                } else {
                    callback(null)
                }
            }

            override fun onFailure(call: Call<List<MovieDetails>>, t: Throwable) {
                callback(null)
            }
        })
    }

    fun getMovieDetail(movieId: Int, callback: (MovieDetails?) -> Unit) {
        movieApi.getMovieDetail(movieId).enqueue(object : Callback<MovieDetails> {
            override fun onResponse(call: Call<MovieDetails>, response: Response<MovieDetails>) {
                if (response.isSuccessful) {
                    callback(response.body())
                } else {
                    callback(null)
                }
            }

            override fun onFailure(call: Call<MovieDetails>, t: Throwable) {
                callback(null)
            }
        })
    }
}