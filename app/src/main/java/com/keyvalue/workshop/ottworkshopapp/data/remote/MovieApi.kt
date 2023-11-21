package com.keyvalue.workshop.ottworkshopapp.data.remote
import com.keyvalue.workshop.ottworkshopapp.domain.model.MovieDetails
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApi {
    @GET("movies")
    fun getMovies(): Call<List<MovieDetails>>

    @GET("movies/{id}")
    fun getMovieDetail(@Path("id") movieId: Int): Call<MovieDetails>
}