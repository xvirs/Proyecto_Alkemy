package com.example.proyect_alkemy.network

import com.example.proyect_alkemy.model.PopularMovies
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url


interface ServiceAPI {



    @GET
    suspend fun getMoviesPopular(@Url url:String): PopularMovies

}
