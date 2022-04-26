package com.example.proyect_alkemy.model

data class PopularMovies (

    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
  /*
    val page: Int?=0,
    val results: List<Result>?= emptyList(),
    val total_pages: Int?=0,
    val total_results: Int?=0

   */
        )