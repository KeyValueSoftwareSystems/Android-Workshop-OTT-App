package com.keyvalue.workshop.ottworkshopapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.keyvalue.workshop.ottworkshopapp.data.repositoryimpl.MovieRepositoryImpl
import com.keyvalue.workshop.ottworkshopapp.domain.model.Movie
import com.keyvalue.workshop.ottworkshopapp.domain.model.MovieDetail

class MovieViewModel: ViewModel() {

    private val movieRepositoryImpl: MovieRepositoryImpl = MovieRepositoryImpl()

    private val _movies = MutableLiveData<Movie>()
    val movies: LiveData<Movie> get() = _movies

    private val _selectedMovie = MutableLiveData<MovieDetail>()
    val selectedMovie: LiveData<MovieDetail> get() = _selectedMovie

    suspend fun getMovies() {
        movieRepositoryImpl.getMovies {
            _movies.postValue(it)
        }
    }

   suspend fun getMovieDetail(movieId: Int) {
        movieRepositoryImpl.getMovieDetail(movieId) {
            _selectedMovie.postValue(it)
        }
    }
}