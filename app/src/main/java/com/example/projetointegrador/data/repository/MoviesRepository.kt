package com.example.projetointegrador.data.repository

import android.net.Uri
import com.example.projetointegrador.data.model.*
import io.reactivex.Observable

//Essa camada de repositório é responsável por acessar os dados e chamar o Retrofit.
class MoviesRepository {

    fun fetchList() : Observable<ListMovies> {
        return NetworkRetrofit.getService().getMovies() //Essa função chama o arquivo de serviços e o retrofit, montando a chamada que trás as informações dos filmes (imagem, título e rating).
    }

    fun fetchDetailsList(movieId : Int) : Observable<ReleaseDatesResponse> {
        return NetworkRetrofit.getService().getReleaseDate(movieId) //Essa função chama o arquivo de serviços e o retrofit, montando a chamada que trás as informações de certificação nos detalhes filmes.
    }

    fun fetchCast(movieId2 : Int) : Observable<ListCast> {
        return NetworkRetrofit.getService().getCast(movieId2) //Essa função chama o arquivo de serviços e o retrofit, montando a chamada que trás as informações dos detalhes do "cast" (imagem, nome e papel).
    }

    fun fetchAllGenres() : Observable<ListAllMoviesGenres> {
        return NetworkRetrofit.getService().getAllMoviesGenres() //Essa função chama o arquivo de serviços e o retrofit, montando a chamada que trás as informações dos gêneros dos filmes.
    }

    fun fetchGenres(movieId3 : Int) : Observable<ListAllMoviesGenres> {
        return NetworkRetrofit.getService().getGenres(movieId3) //Essa função chama o arquivo de serviços e o retrofit, montando a chamada que trás as informações dos gêneros de cada filme.
    }

    fun fetchSelectGenres(movieId4 : String) : Observable<ListMovies> {
        return NetworkRetrofit.getService().getSelectGenres(movieId4) //Essa função chama o arquivo de serviços e o retrofit, montando a chamada que trás as informações para que o "chip button" consiga selecionar os filmes por gênero.
    }

    fun fetchRuntime(movieId5 : Int) : Observable<Runtime> {
        return NetworkRetrofit.getService().getRuntime(movieId5) //Essa função chama o arquivo de serviços e o retrofit, montando a chamada que trás as informações de "runtime" dos detalhes dos filmes.
    }

    fun fetchSearch(movieSearched: Uri) : Observable<ListMovies> {
        return NetworkRetrofit.getService().searchForMovie(movieSearched) //Essa função chama o arquivo de serviços e o retrofit, montando a chamada que trás as informações de "runtime" dos detalhes dos filmes.
    }

}