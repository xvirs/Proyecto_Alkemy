package com.example.proyect_alkemy.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.proyect_alkemy.R
import com.example.proyect_alkemy.model.Result

class MoviesAdapter(private val movies : List<Result>) :
    RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_movie, parent, false)
            return ViewHolder(view )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val  movie = movies[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = movies.size


    class ViewHolder(val view : View ) : RecyclerView.ViewHolder(view) {

        private val portada = view.findViewById<ImageView>(R.id.portada)
        private val title = view.findViewById<TextView>(R.id.title)

        fun bind(movie: Result) {

            title.text = movie.title
            Glide.with(portada.context).load("https://image.tmdb.org/t/p/original"+movie.poster_path).into(portada)



        }
    }


}