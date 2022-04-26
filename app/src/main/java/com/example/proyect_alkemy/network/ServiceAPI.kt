package com.example.proyect_alkemy.network

import com.example.proyect_alkemy.model.PopularMovies
import retrofit2.Response
import retrofit2.http.GET

interface ServiceAPI {

    @GET("/3/movie/popular?api_key=e34e542dda9bfc0d277e4f73eddfeaee&language=es-ES&page=1")
    suspend fun getMoviesPopular(): PopularMovies

}