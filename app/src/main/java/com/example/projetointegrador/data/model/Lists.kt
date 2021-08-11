package com.example.projetointegrador.data.model

data class ListMovies (var results : MutableList<Infos>)

data class ListCast (val id : Int,
                     val cast: List<InfosCast>)

data class ListAllMoviesGenres (var genres : List<AllMoviesGenres>)