package com.example.projetointegrador.data.model

import android.os.Build
import androidx.annotation.RequiresApi
import java.util.*
import kotlin.collections.ArrayList

class Favorites {

   @RequiresApi(Build.VERSION_CODES.N)
    open fun addToFavorites(movie: Infos) {
        favoritesList.putIfAbsent(movie.id, movie)
    }

    fun removeFromFavorites(movieId : Int) {
        favoritesList.remove(movieId)
    }

    fun listFavoritesMovies(): MutableList<Infos> {
        return ArrayList(favoritesList.values)
    }

    private companion object {
        val favoritesList : MutableMap<Int, Infos> = mutableMapOf()
    }

}