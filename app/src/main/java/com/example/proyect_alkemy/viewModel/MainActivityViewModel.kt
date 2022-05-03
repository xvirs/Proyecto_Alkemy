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

    fun getMovie(){
        viewModelScope.launch {
            myResponse.value = movieNetworck.retrofit.getMoviesPopular()
        }
    }

     fun getDetail(){
         viewModelScope.launch {
             myResponse.value = movieNetworck.retrofit.getMoviesPopular()
         }
     }





}