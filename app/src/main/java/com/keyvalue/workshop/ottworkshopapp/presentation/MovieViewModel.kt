package com.keyvalue.workshop.ottworkshopapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.keyvalue.workshop.ottworkshopapp.data.repositoryimpl.MovieRepositoryImpl
import com.keyvalue.workshop.ottworkshopapp.domain.model.MovieDetails

class MovieViewModel(): ViewModel() {

    private val movieRepositoryImpl: MovieRepositoryImpl
    init {
        movieRepositoryImpl = MovieRepositoryImpl()
    }

    private val _movies = MutableLiveData<List<MovieDetails>>()
    val movies: LiveData<List<MovieDetails>> get() = _movies

    private val _selectedMovie = MutableLiveData<MovieDetails>()
    val selectedMovie: LiveData<MovieDetails> get() = _selectedMovie

    fun getMovies() {
        movieRepositoryImpl.getMovies {
            _movies.postValue(it)
        }
    }

    fun selectMovie(movie: MovieDetails) {
        _selectedMovie.value = movie
    }

    fun clearSelectedMovie() {
        _selectedMovie.value = null
    }

    fun getMovieDetail(movieId: Int) {
        movieRepositoryImpl.getMovieDetail(movieId) {
            _selectedMovie.postValue(it)
        }
    }
}