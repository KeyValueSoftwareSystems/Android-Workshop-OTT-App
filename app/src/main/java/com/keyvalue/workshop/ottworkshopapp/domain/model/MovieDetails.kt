package com.keyvalue.workshop.ottworkshopapp.domain.model

import java.io.Serializable

data class MovieDetails(var id: Int, var name: String, val isKidsMovie: Boolean) : Serializable {
}