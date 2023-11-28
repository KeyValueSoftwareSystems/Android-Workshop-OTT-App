package com.keyvalue.workshop.ottworkshopapp.data.remote


import com.keyvalue.workshop.ottworkshopapp.domain.model.Movie
import com.keyvalue.workshop.ottworkshopapp.domain.model.MovieDetail
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET("movie/now_playing")
   suspend fun getPopularMovies( @Query("language") language: String,@Query("page") page:Int,@Header("Authorization") token: String,@Header("accept") accept: String="application/json"): Response<Movie>
    @GET("movie/{id}")
   suspend fun getMovieDetail(@Path("id") movieID:Int , @Query("language") language: String,@Header("Authorization") token: String,@Header("accept") accept: String="application/json"):Response<MovieDetail>
}