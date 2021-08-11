package com.example.projetointegrador.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Infos (val id: Int,
                  val vote_average : Number,
                  val title : String,
                  val poster_path : String,
                  val backdrop_path : String,
                  val overview : String,
                  val release_date : String,
                  val runtime : Int?,
                  var favoriteCheck : Boolean = false): Parcelable

//As classes a seguir são para implementação do item "certification" de "release_dates".
@Parcelize
data class ReleaseDatesResponse (
    val id : Int,
    val results: List<GuidanceResponse>) : Parcelable {

    @Parcelize
    data class GuidanceResponse(
        val iso_3166_1: String,
        val release_dates: List<ReleaseDate>
    ) : Parcelable

    @Parcelize
    data class ReleaseDate(val  certification: String,
                                 val type : Int) : Parcelable

    @Parcelize
    data class ReleaseInfo(val certification: ReleaseInfo) : Parcelable

    override fun toString(): String {
        var certIso = ""
            for (i in results) {
              if(i.iso_3166_1 == "BR") {
               certIso += i.release_dates[0].certification
              }
            }
               return certIso
    }

}

//A classe a seguir é para implementação dos detalhes do "cast".
@Parcelize
data class InfosCast(val profile_path : String?,
                     val name : String,
                     val character : String) : Parcelable

//A classe a seguir é para implementação dos gêneros de filmes.
@Parcelize
data class AllMoviesGenres (val id : Int,
                            val name : String) : Parcelable


//A classe a seguir é para implementação do "runtime" dos filmes.
@Parcelize
data class Runtime (val id: Int,
                    val runtime : Int): Parcelable

