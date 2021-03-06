package com.example.proyect_alkemy.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.proyect_alkemy.R
import com.example.proyect_alkemy.model.Result
import com.example.proyect_alkemy.viewModel.MainActivityViewModel
import java.io.IOException

class DetailActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)

        val viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        viewModel.getMovie(null)
        viewModel.myResponse.observe(this, Observer {

            if(it.results.isEmpty()){
                Toast.makeText(this, "no se puede acceder a los datos solicitados", Toast.LENGTH_SHORT).show()
            }else {

                try {
                    val movie = intent.getSerializableExtra("movie") as Result

                    val imagen = findViewById<ImageView>(R.id.imagen)
                    val title = findViewById<TextView>(R.id.titulo1)
                    val descripcion = findViewById<TextView>(R.id.descripcion)
                    val fechaEstreno = findViewById<TextView>(R.id.fechaEstreno)
                    val idioma = findViewById<TextView>(R.id.idioma)
                    val popularidad = findViewById<TextView>(R.id.popularidad)

                    Glide.with(imagen.context)
                        .load("https://image.tmdb.org/t/p/original" + movie.backdrop_path).into(imagen)
                    title.text = movie.title
                    descripcion.text = movie.overview
                    fechaEstreno.text = "Fecha de estreno : " + movie.release_date
                    idioma.text = "Idioma original : " + movie.original_language
                    popularidad.text = "Likes : " + movie.popularity
                }catch (e: IOException){
                    Toast.makeText(this, "no se pudo acceder a la informacion", Toast.LENGTH_SHORT).show()
                }

            }})
    }



}
