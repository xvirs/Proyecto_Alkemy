package com.example.proyect_alkemy.view

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.proyect_alkemy.R
import com.example.proyect_alkemy.viewModel.MainActivityViewModel

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        
        val textoPrueba = findViewById<TextView>(R.id.texto_prueba)

        viewModel.getMovie()
        viewModel.myResponse.observe(this, Observer {

            textoPrueba.text = it.results[1].poster_path

        })

    }
}