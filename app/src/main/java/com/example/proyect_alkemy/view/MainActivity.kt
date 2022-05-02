package com.example.proyect_alkemy.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyect_alkemy.R
import com.example.proyect_alkemy.viewModel.MainActivityViewModel


class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        viewModel.getMovie()
        viewModel.myResponse.observe(this, Observer {

            val recycler = findViewById<RecyclerView>(R.id.recycler)

            val layoutManager = GridLayoutManager(this,3)
            recycler.layoutManager = layoutManager

            recycler.adapter = MoviesAdapter( it.results.sortedByDescending { it.title})


        })
    }


}

