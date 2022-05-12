package com.example.proyect_alkemy.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieNetworck {


    val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ServiceAPI::class.java)
    }

}