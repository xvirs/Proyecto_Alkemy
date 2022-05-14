package com.example.proyect_alkemy.view

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyect_alkemy.R
import com.example.proyect_alkemy.viewModel.MainActivityViewModel
import java.io.IOException


class MainActivity : AppCompatActivity()  {


    private var pagi : Int = 1
    private var ini : String= "pagina  ${pagi}  de  33404..."



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnSiguiente = findViewById<Button>(R.id.siguiente)
        val btnAnterior = findViewById<Button>(R.id.anterior)
        val indice = findViewById<TextView>(R.id.indice)


        try {
            cargarPagina(pagi)
            indice.text = "pagina  ${pagi}  de  33404..."
            btnSiguiente.setOnClickListener { pagSiguiente() }
            btnAnterior.setOnClickListener { pagAnterior() }
        } catch (e: IOException) {
            Toast.makeText(this, "no se pudo acceder a la informacion", Toast.LENGTH_SHORT).show()
        }



    }


    private fun pagSiguiente(){
        if (pagi <= 33404){
            pagi++
            cargarPagina(pagi)
            val indice = findViewById<TextView>(R.id.indice)
            indice.text= "pagina  ${pagi}  de  33404..."
        }else {
            Toast.makeText(this, "No hay mas paginas disponibles", Toast.LENGTH_SHORT).show()
        }
    }

    private fun pagAnterior(){
        if (pagi > 1){
            pagi--
            cargarPagina(pagi)
            val indice = findViewById<TextView>(R.id.indice)
            indice.text= "pagina  ${pagi}  de  33404..."
        }else {
            Toast.makeText(this, "No hay mas paginas disponibles", Toast.LENGTH_SHORT).show()
        }
    }


    private fun cargarPagina(pagina: Int) {
        val viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.getMovie(pagina)
        viewModel.myResponse.observe(this, Observer {

            val recycler = findViewById<RecyclerView>(R.id.recycler)

            val layoutManager = GridLayoutManager(this,3)
            recycler.layoutManager = layoutManager

            recycler.adapter = MoviesAdapter( it.results,this)

            println("Se cargo la pagina")

        })
    }


}

