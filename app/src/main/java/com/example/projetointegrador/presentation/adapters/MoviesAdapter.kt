package com.example.projetointegrador.presentation.adapters

import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.ToggleButton
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.projetointegrador.R
import com.example.projetointegrador.data.model.Infos
import com.bumptech.glide.Glide
import com.example.projetointegrador.data.repository.MoviesService
import com.example.projetointegrador.presentation.MoviesDetailsActivity
import com.example.projetointegrador.presentation.MoviesViewModel

class MoviesAdapter(val context: Context, var dataSet: MutableList<Infos> = mutableListOf()) : RecyclerView.Adapter<MoviesAdapter.RecyclerviewViewHolder>() {

    var favoritechecked : (movie: Infos, isChecked: Boolean) -> Unit = { _, _ ->}

    class RecyclerviewViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var imageMovie = view.findViewById<ImageView>(R.id.imgMovie)
        var movieTitle = view.findViewById<TextView>(R.id.txtMovieTitle)
        var rating = view.findViewById<TextView>(R.id.txtRating)
        var favoriteButton = view.findViewById<ToggleButton>(R.id.btnFavorite)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerviewViewHolder = RecyclerviewViewHolder (
       LayoutInflater.from(parent.context).inflate(R.layout.fragment_movies, parent, false)
    )

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(holder: RecyclerviewViewHolder, position: Int) {

        if(dataSet[position].poster_path !== ""){
            holder.imageMovie?.let { Glide.with(context).load("https://image.tmdb.org/t/p/w500" + dataSet[position].poster_path).into(it) }
        }
        holder.movieTitle.text = dataSet[position].title
        holder.rating.text = dataSet[position].vote_average.toString()  + " %"
        holder.imageMovie?.setOnClickListener {
            val intent = Intent(context, MoviesDetailsActivity::class.java)
            intent.putExtra("movies", dataSet[position])
            context.startActivity(intent)
        }

        holder.favoriteButton?.isChecked = dataSet[position].favoriteCheck
        holder.favoriteButton?.setOnClickListener {
            favoritechecked(dataSet[position], !dataSet[position].favoriteCheck)
            notifyDataSetChanged()
        }

    }

    override fun getItemCount() = dataSet.size

}


/*holder.favoriteButton?.isChecked = dataSet[position].favoriteCheck

holder.favoriteButton?.setOnClickListener {
    dataSet[position].favoriteCheck = true
    favoritesClass.addToFavorites(dataSet[position])
        //-=p

        //Log.d("gg",lll.favoritesList.keys.toString())

        favoritesClass.removeFromFavorites(dataSet[position].id)
    //Log.d("gg",favoritesClass.favoritesList.size.toString())
    }*/
