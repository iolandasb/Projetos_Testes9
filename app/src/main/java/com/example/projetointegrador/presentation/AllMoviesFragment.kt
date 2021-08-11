package com.example.projetointegrador.presentation

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projetointegrador.R
import com.example.projetointegrador.data.model.Infos
import com.example.projetointegrador.presentation.adapters.GenresAdapter
import com.example.projetointegrador.presentation.adapters.MoviesAdapter

class AllMoviesFragment : Fragment() {

    private val viewModel = MoviesViewModel()

    lateinit var listAdapter: MoviesAdapter
    lateinit var container: RecyclerView

    lateinit var genresAdapter: GenresAdapter
    lateinit var containerGenres: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_tab_allmovies, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        //Chamada da Recyclerview do MoviesAdapter
        container = view.findViewById(R.id.rcvContainer)
        listAdapter = MoviesAdapter(context = view.context)
        container.adapter = listAdapter
        container.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)

        //Chamada da Recyclerview do GenresAdapter.
        containerGenres = view.findViewById(R.id.rcvAllMoviesTypes)
        genresAdapter = GenresAdapter(context = view.context)
        containerGenres.adapter = genresAdapter
        containerGenres.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)

        listAdapter.favoritechecked = { movie, isChecked ->
            if (isChecked) {
                movie.favoriteCheck = true
                viewModel.addFavorite(movie)
            /*}else{
                movie.favoriteCheck = false
                viewModel.removeFavorite(movie.id)*/
            }
        }

        //Aqui se está está dizendo que se lista com os "ids" estiver vazia, deve-se acessar todos os filmes, conforme a função "getInfos()", e caso não esteja, deve-se selecionar os "ids" de acordo com a função "getGenresSelect()".
        genresAdapter.genresChecked = { movieId4 ->
            if (movieId4.isEmpty())
                viewModel.getInfos()
            else
                viewModel.getGenresSelect(movieId4)
        }

        setupObserveList()
        viewModel.getInfos()

        //Aqui se está chamando as informações enviadas pela função da ViewModel referente aos gêneros dos filmes.
        setupGenresObserveList()
        viewModel.getAllGenresInfos()

    }

    //Aqui se está dizendo "observe a viewmodel e quando algo acontecer a ela, a atrele ao adapter.
    fun setupObserveList() {
        viewModel.moviesLiveData.observe(viewLifecycleOwner,
            { response ->
                response?.let {
                    listAdapter.dataSet.clear()
                    listAdapter.dataSet.addAll(it)
                    listAdapter.notifyDataSetChanged()
                }
            }
        )
    }

    //Aqui se está dizendo "observe a viewmodel e quando algo acontecer a ela a atrele ao adapter".
    fun setupGenresObserveList() {
        viewModel.allGenresLiveData.observe(viewLifecycleOwner,
            { response ->
                response?.let {
                    genresAdapter.dataSetGenres.clear()
                    genresAdapter.dataSetGenres.addAll(it)
                    genresAdapter.notifyDataSetChanged()
                }
            })
    }

}
