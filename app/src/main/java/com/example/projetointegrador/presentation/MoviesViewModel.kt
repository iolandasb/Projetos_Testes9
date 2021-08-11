package com.example.projetointegrador.presentation

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.projetointegrador.data.model.*
import com.example.projetointegrador.data.model.Favorites
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

//Aqui na viewmodel não deve ser inseridas funções para visibilidade (isso fica na activity) do usuário, e sim funções de dados.
class MoviesViewModel  : ViewModel() {

    //LiveData relacionada com a lista que trás os itens iniciais dos filmes (imagem, título e rating)
    private val _moviesLiveData = MutableLiveData<MutableList<Infos>>(mutableListOf()) //QUEM VÊ É A VIEWMODEL.
    val moviesLiveData: LiveData<MutableList<Infos>> = _moviesLiveData //QUEM VÊ É A ACTIVITY

    //LiveData relacionada com os detalhes dos filmes (imagem, título, rating, sinopse, ano, certificação e duração)
    private val _moviesDetailsLiveData = MutableLiveData<ReleaseDatesResponse>()
    val detailsLiveData: LiveData<ReleaseDatesResponse> = _moviesDetailsLiveData

    //LiveData relacionada com os detalhes do "cast" (imagem, nome, rating, função)
    private val _castLiveData = MutableLiveData<List<InfosCast>>() //QUEM VÊ É A VIEWMODEL.
    val castLiveData: LiveData<List<InfosCast>> = _castLiveData //QUEM VÊ É A ACTIVITY

    //LiveData relacionada com os gêneros de filmes
    private val _allGenresLiveData = MutableLiveData<List<AllMoviesGenres>>() //QUEM VÊ É A VIEWMODEL.
    val allGenresLiveData: LiveData<List<AllMoviesGenres>> = _allGenresLiveData //QUEM VÊ É A ACTIVITY

    //LiveData relacionada com item "runtime" de cada filme.
    private val _runtimeLiveData = MutableLiveData<Runtime>() //QUEM VÊ É A VIEWMODEL.
    val runtimeLiveData: LiveData<Runtime> = _runtimeLiveData //QUEM VÊ É A ACTIVITY

    val _favoriteMoviesLiveData = MutableLiveData<MutableList<Infos>>(mutableListOf()) //QUEM VÊ É A VIEWMODEL.
    //val favoriteMoviesLiveData: LiveData<List<Infos>> = _favoriteMoviesLiveData //QUEM VÊ É A ACTIVITY

    private val _searchLiveData = MutableLiveData<MutableList<Infos>>(mutableListOf()) //QUEM VÊ É A VIEWMODEL.
    val searchLiveData: LiveData<MutableList<Infos>> = _searchLiveData //QUEM VÊ É A ACTIVITY

    var favorites = Favorites()

    //Nas funções abaixo se está chamando o retrofit e o serviço, os quais passam pelo repositório e pelo fetch (por questão de estrutura).

    @SuppressLint("CheckResult")
    fun getInfos() {
        val fetchMoviesUseCase = FetchMoviesUseCase() //Aqui se chamou uma classe de dados.
        fetchMoviesUseCase.execute()
            .subscribeOn(Schedulers.io()) //Processamento de entrada e saída de dados.
            .observeOn(AndroidSchedulers.mainThread()) //"mainThread" se refere a threads relacionadas a componentes Android, ou seja, que aparecem para o usuário (UI). Não é só a MainActivity.
            /*.doOnError { //Esse comando serve para incluir algo quando houver erro (como por exemplo adicionar a activity (de layout) de erro.
                //Faça algo se der erro na REQUEST <
            }*/
            .subscribe {
                _moviesLiveData.value = it.results //Esse comando significa que quando a chamada for feita, será atribuído um valor ao Livedata, e irá atualizar a variável "moviesLiveData" de modo que essa nova informação será o que a activity vai visualizar.
            }
    }

    @SuppressLint("CheckResult")
    fun getInfosDetails(movieId : Int) {
        val fetchDetailsUseCase = FetchDetailsUseCase(movieId = movieId) //Aqui se chamou uma classe de dados.
        fetchDetailsUseCase.execute()
            .subscribeOn(Schedulers.io()) //Processamento de entrada e saída de dados.
            .observeOn(AndroidSchedulers.mainThread()) //"mainThread" se refere a threads relacionadas a componentes Android, ou seja, que aparecem para o usuário (UI). Não é só a MainActivity.
            /*.doOnError { //Esse comando serve para incluir algo quando houver erro (como por exemplo adicionar a activity (de layout) de erro.
                //Faça algo se der erro na REQUEST
            }*/
            .subscribe {
                _moviesDetailsLiveData.value = it //Esse comando significa que quando a chamada for feita, será atribuído um valor ao Livedata, e irá atualizar a variável "moviesDetailsLiveData" de modo que essa nova informação será o que a activity vai visualizar.
            }
    }

    @SuppressLint("CheckResult")
    fun getCastInfos(movieId2 : Int) {
        val fetchCastUseCase = FetchCastUseCase(movieId2 = movieId2) //Aqui se chamou uma classe de dados.
        fetchCastUseCase.execute()
            .subscribeOn(Schedulers.io()) //Processamento de entrada e saída de dados.
            .observeOn(AndroidSchedulers.mainThread()) //"mainThread" se refere a threads relacionadas a componentes Android, ou seja, que aparecem para o usuário (UI). Não é só a MainActivity.
            /*.doOnError { //Esse comando serve para incluir algo quando houver erro (como por exemplo adicionar a activity (de layout) de erro.
                //Faça algo se der erro na REQUEST <
            }*/
            .subscribe {
                _castLiveData.value = it.cast //Esse comando significa que quando a chamada for feita, será atribuído um valor ao Livedata, e irá atualizar a variável "castLiveData" de modo que essa nova informação será o que a activity vai visualizar.
            }
    }

    @SuppressLint("CheckResult")
    fun getAllGenresInfos() {
        val fetchAllGenresCastUseCase = FetchAllGenresUseCase() //Aqui se chamou uma classe de dados.
        fetchAllGenresCastUseCase.execute()
            .subscribeOn(Schedulers.io()) //Processamento de entrada e saída de dados.
            .observeOn(AndroidSchedulers.mainThread()) //"mainThread" se refere a threads relacionadas a componentes Android, ou seja, que aparecem para o usuário (UI). Não é só a MainActivity.
            /*.doOnError { //Esse comando serve para incluir algo quando houver erro (como por exemplo adicionar a activity (de layout) de erro.
                //Faça algo se der erro na REQUEST <
            }*/
            .subscribe {
                _allGenresLiveData.value = it.genres //Esse comando significa que quando a chamada for feita, será atribuído um valor ao Livedata, e irá atualizar a variável "allGenresLiveData" de modo que essa nova informação será o que a activity vai visualizar.
            }
    }

    @SuppressLint("CheckResult")
    fun getGenresInfos(movieId3 : Int) {
        val fetchGenresCastUseCase = FetchGenresUseCase(movieId3 = movieId3) //Aqui se chamou uma classe de dados.
        fetchGenresCastUseCase.execute()
            .subscribeOn(Schedulers.io()) //Processamento de entrada e saída de dados.
            .observeOn(AndroidSchedulers.mainThread()) //"mainThread" se refere a threads relacionadas a componentes Android, ou seja, que aparecem para o usuário (UI). Não é só a MainActivity.
            /*.doOnError { //Esse comando serve para incluir algo quando houver erro (como por exemplo adicionar a activity (de layout) de erro.
                //Faça algo se der erro na REQUEST <
            }*/
            .subscribe {
                _allGenresLiveData.value = it.genres //Esse comando significa que quando a chamada for feita, será atribuído um valor ao Livedata, e irá atualizar a variável "allGenresLiveData" de modo que essa nova informação será o que a activity vai visualizar.
            }
    }

    @SuppressLint("CheckResult")
    fun getGenresSelect(movieId4 : List<Int>) {
        val fetchSelectGenresUseCase = FetchSelectGenresUseCase(movieId4 = movieId4) //Aqui se chamou uma classe de dados.
        fetchSelectGenresUseCase.execute()
            .subscribeOn(Schedulers.io()) //Processamento de entrada e saída de dados.
            .observeOn(AndroidSchedulers.mainThread()) //"mainThread" se refere a threads relacionadas a componentes Android, ou seja, que aparecem para o usuário (UI). Não é só a MainActivity.
            /*.doOnError { //Esse comando serve para incluir algo quando houver erro (como por exemplo adicionar a activity (de layout) de erro.
                //Faça algo se der erro na REQUEST <
            }*/
            .subscribe {
                _moviesLiveData.value = it.results //Esse comando significa que quando a chamada for feita, será atribuído um valor ao Livedata, e irá atualizar a variável "moviesLiveData" de modo que essa nova informação será o que a activity vai visualizar.
            }
    }

    @SuppressLint("CheckResult")
    fun getMoviesRuntime(movieId5 : Int) {
        val fetchRuntimeUseCase = FetchRuntimeUseCase(movieId5 = movieId5) //Aqui se chamou uma classe de dados.
        fetchRuntimeUseCase.execute() //Aqui é executada a classe de "FetchMoviesUserCase".
            .subscribeOn(Schedulers.io()) //Processamento de entrada e saída de dados.
            .observeOn(AndroidSchedulers.mainThread()) //"mainThread" se refere a threads relacionadas a componentes Android, ou seja, que aparecem para o usuário (UI). Não é só a MainActivity.
            /*.doOnError { //Esse comando serve para incluir algo quando houver erro (como por exemplo adicionar a activity (de layout) de erro.
                //Faça algo se der erro na REQUEST <
            }*/
            .subscribe {
                _runtimeLiveData.value = it //Esse comando significa que quando a chamada for feita, será atribuído um valor ao Livedata, e irá atualizar a variável "runtimeLiveData" de modo que essa nova informação será o que a activity vai visualizar.
            }
    }

    @SuppressLint("CheckResult")
    fun getSearch(movieSearched: Uri) {
        val fetchSearchUseCase = FetchSearchUseCase(movieSearched = movieSearched) //Aqui se chamou uma classe de dados.
        fetchSearchUseCase.execute()
            .subscribeOn(Schedulers.io()) //Processamento de entrada e saída de dados.
            .observeOn(AndroidSchedulers.mainThread()) //"mainThread" se refere a threads relacionadas a componentes Android, ou seja, que aparecem para o usuário (UI). Não é só a MainActivity.
            /*.doOnError { //Esse comando serve para incluir algo quando houver erro (como por exemplo adicionar a activity (de layout) de erro.
                //Faça algo se der erro na REQUEST <
            }*/
            .subscribe {
                _searchLiveData.value = it.results //Esse comando significa que quando a chamada for feita, será atribuído um valor ao Livedata, e irá atualizar a variável "moviesLiveData" de modo que essa nova informação será o que a activity vai visualizar.
            }
    }

    fun getFavoriteMovies() {
        val favoritesData = favorites.listFavoritesMovies()
        _favoriteMoviesLiveData.value = favoritesData
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun addFavorite(movie: Infos) {
        favorites.addToFavorites(movie)
        _favoriteMoviesLiveData.value = favorites.listFavoritesMovies()
    }

    fun removeFavorite(movieId: Int) {
        favorites.removeFromFavorites(movieId)
        _favoriteMoviesLiveData.value = favorites.listFavoritesMovies()
    }

}