package com.example.projetointegrador.data.repository

import android.net.Uri
import com.example.projetointegrador.data.model.*
import retrofit2.http.GET
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.Path
import retrofit2.http.Query

//Essa classe implementa a rota de requisição do retrofit.
//A query obrigatória de "api_key" foi informada no Retrofit, motivo pelo qual não consta aqui.
interface MoviesService {

    //Link de filmes populares.
    @GET("movie/popular")
    fun getMovies(): Observable<ListMovies> //O comando observa um objeto de tipo "ListMovies", para aí então ativar o código.

    //Link de detalhes ds filmes.
    @GET("movie/{movie_id}/release_dates")
    fun getReleaseDate(@Path("movie_id") movie_id: Int) : Observable<ReleaseDatesResponse> //O comando observa um objeto de tipo "ReleaseDatesResponse", para aí então ativar o código. Foi usado "@Path" pois a documentação assim exige para implementação do "id" (https://developers.themoviedb.org/3/movies/get-movie-details).

    //Link do cast.
    @GET("movie/{movie_id}/credits")
    fun getCast(@Path("movie_id") movie_id2: Int): Observable<ListCast> //O comando observa um objeto de tipo "LisCast", para aí então ativar o código. Foi usado "@Path" pois a documentação assim exige para implementação do "id" (https://developers.themoviedb.org/3/movies/get-movie-credits).

    //Link dos gêneros de filmes.
    @GET("genre/movie/list")
    fun getAllMoviesGenres(): Observable<ListAllMoviesGenres> //O comando observa um objeto de tipo "ListAllMoviesGenres", para aí então ativar o código.

    //Link dos gêneros de cada filme.
    @GET("movie/{movie_id}")
    fun getGenres(@Path("movie_id") movie_id3: Int): Observable<ListAllMoviesGenres> //O comando observa um objeto de tipo "ListAllMoviesGenres", para aí então ativar o código. Foi usado "@Path" pois a documentação assim exige para implementação do "id" (https://developers.themoviedb.org/3/movies/get-movie-details).

    //Link da busca de filme por gênero.
    @GET("discover/movie")
    fun getSelectGenres(@Query("with_genres") movie_id4: String): Observable<ListMovies> //O comando observa um objeto de tipo "ListMovies", para aí então ativar o código. Foi usado "@Query" pois a documentação assim exige para implementação do "with_genres" (https://developers.themoviedb.org/3/discover/movie-discover).

    //Link do item "runtime" de cada filme.
    @GET("movie/{movie_id}")
    fun getRuntime(@Path("movie_id") movie_id5: Int): Observable<Runtime> //O comando observa um objeto de tipo "Runtime", para aí então ativar o código. Foi usado "@Path" pois a documentação assim exige para implementação do "id" (https://developers.themoviedb.org/3/movies/get-movie-details).

    @GET("search/movie")
    fun searchForMovie(@Query("query") movieSearched: Uri): Observable<ListMovies>

}