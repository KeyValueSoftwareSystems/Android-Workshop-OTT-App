package com.keyvalue.workshop.ottworkshopapp.data.remote

import com.keyvalue.workshop.ottworkshopapp.domain.model.MovieDetails
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET("movie/popular")
    fun getPopularMovies(@Query("api_key") apiKey: String): Call<List<MovieDetails>>
    @GET("movie/{id}")
    fun getMovieDetail(@Path("id") movieId: Int, @Query("api_key") apiKey: String): Call<MovieDetails>
}