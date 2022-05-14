package com.example.proyect_alkemy.view

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.proyect_alkemy.R
import com.example.proyect_alkemy.model.PopularMovies
import com.example.proyect_alkemy.model.Result
import com.example.proyect_alkemy.viewModel.MainActivityViewModel
import retrofit2.Response
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


        if(isConnected(this)){
            try {

                cargarPagina(pagi)
                indice.text = "pagina  ${pagi}  de  33404..."

                btnSiguiente.setOnClickListener {
                    if(isConnected(this)){
                        pagSiguiente()
                    }else{
                        Toast.makeText(this, "Conecion Fallida : asegúrese  tener acceso a internet para continuar", Toast.LENGTH_SHORT).show()
                    }
                }
                btnAnterior.setOnClickListener {
                    if(isConnected(this)){
                        pagAnterior()
                    }else{
                        Toast.makeText(this, "Conecion Fallida : asegúrese  tener acceso a internet para continuar", Toast.LENGTH_SHORT).show()
                    }
                }

            } catch (e: IOException) {
                Toast.makeText(this, "no se pudo acceder a la informacion solicitada", Toast.LENGTH_SHORT).show()
            }

        }else{
            Toast.makeText(this, "Conecion Fallida : asegúrese  tener acceso a internet para continuar", Toast.LENGTH_SHORT).show()
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


            if(it.results.isEmpty()){
                Toast.makeText(this, "No hay mas paginas disponibles", Toast.LENGTH_SHORT).show()
            }else{

                val recycler = findViewById<RecyclerView>(R.id.recycler)

                val layoutManager = GridLayoutManager(this,3)
                recycler.layoutManager = layoutManager

                recycler.adapter = MoviesAdapter( it.results,this)

            }

        })
    }


    fun isConnected(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager != null) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                    return true
                }
            }
        }
        return false
    }

}


