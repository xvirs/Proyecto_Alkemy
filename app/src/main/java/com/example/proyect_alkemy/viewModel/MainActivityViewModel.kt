package com.example.proyect_alkemy.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyect_alkemy.model.PopularMovies
import com.example.proyect_alkemy.network.MovieNetworck
import kotlinx.coroutines.launch

 class MainActivityViewModel : ViewModel() {

    private val movieNetworck = MovieNetworck()
    val myResponse: MutableLiveData<PopularMovies> = MutableLiveData()

    private val link : String = "3/movie/popular?api_key=e34e542dda9bfc0d277e4f73eddfeaee&language=es-ES&page="

    fun getMovie(page : Int?){

        viewModelScope.launch {
            myResponse.value = movieNetworck.retrofit.getMoviesPopular(link+page)
        }

    }







}