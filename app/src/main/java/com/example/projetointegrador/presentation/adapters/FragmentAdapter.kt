package com.example.projetointegrador.presentation.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.fragment.app.FragmentActivity
import com.example.projetointegrador.presentation.AllMoviesFragment
import com.example.projetointegrador.presentation.FavoritesFragment

//O viewpager necessita da implementação de um adapter, o qual necessariamente deve conter as funções abaixo.
class FragmentAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> AllMoviesFragment()
            1 -> FavoritesFragment()
            else -> AllMoviesFragment()
        }
    }

}