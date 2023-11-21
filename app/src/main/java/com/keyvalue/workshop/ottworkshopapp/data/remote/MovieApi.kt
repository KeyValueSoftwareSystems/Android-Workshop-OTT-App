package com.keyvalue.workshop.ottworkshopapp.data.remote


import com.keyvalue.workshop.ottworkshopapp.domain.model.Movie
import com.keyvalue.workshop.ottworkshopapp.domain.model.MovieDetail
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET("movie/now_playing")
    fun getPopularMovies( @Query("language") language: String,@Query("page") page:Int,@Header("Authorization") token: String,@Header("accept") accept: String="application/json"): Call<Movie>
    @GET("movie/{id}")
    fun getMovieDetail(@Path("id") movieID:Int , @Query("language") language: String,@Header("Authorization") token: String,@Header("accept") accept: String="application/json"): Call<MovieDetail>
}