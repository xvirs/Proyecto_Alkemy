package com.example.proyect_alkemy.view

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.proyect_alkemy.R
import com.example.proyect_alkemy.model.Result
import java.io.IOException

class MoviesAdapter(private val movies: List<Result>, private var contexto: Context) : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_movie, parent, false)
        return ViewHolder(view, contexto)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)  {
        val  movie = movies[position]

        try {
            holder.bind(movie)
        } catch (e: IOException) {
            Toast.makeText(contexto, "no se pudo acceder a la informacion", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int = movies.size


    class ViewHolder(val view : View, var contexto : Context) : RecyclerView.ViewHolder(view) {

        private val portada = view.findViewById<ImageView>(R.id.portada)
        private val title = view.findViewById<TextView>(R.id.titulo)


        fun bind(movie: Result) {

            title.text = movie.title
            Glide.with(portada.context).load("https://image.tmdb.org/t/p/original"+movie.poster_path).into(portada)

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
            view.setOnClickListener(){
            if (isConnected(contexto)){

                    contexto.startActivity(Intent(contexto, DetailActivity::class.java).putExtra("movie", movie))

            }else{
                Toast.makeText(contexto, "Conecion Fallida : asegúrese  tener acceso a internet para continuar", Toast.LENGTH_SHORT).show()
            }}

        }
    }
}







