package com.example.projetointegrador.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.*
import androidx.core.net.toUri
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import androidx.viewpager2.widget.ViewPager2
import com.example.projetointegrador.R
import com.example.projetointegrador.presentation.adapters.FragmentAdapter

class MainActivity : AppCompatActivity() {

    /*ProgressBar:
    private lateinit var loading: ProgressBar -> Exemplo de ProgressBar*/
    var fragment : SearchFragment? = null
    private lateinit var searchButton : ImageButton
    private var searchText : EditText? = null
    private lateinit var icon : ImageView
    private lateinit var searchMode : TextView
    private lateinit var tryAgain : TextView
    private lateinit var searchFragment : FrameLayout
    private lateinit var viewpager : ViewPager2
    private lateinit var tablayout : TabLayout
    private lateinit var movieSearchText : String

    val pageAdapter by lazy { FragmentAdapter(this)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //loading = findViewById(R.id.loading) -> Exemplo de ProgressBar

        //Tablayout e Viewpager2

        bindView()
        viewpager.adapter = FragmentAdapter(this)
        TabLayoutMediator(tablayout, viewpager) { tab, position ->
            tab.text = getTabTitle(position)
        }.attach()
        viewpager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
            }
        })
        applySearch()
}

    fun bindView() {
        searchButton = findViewById(R.id.btnSearch)
        searchText = findViewById(R.id.edtSearch)
        icon = findViewById(R.id.imgRectangle)
        searchMode = findViewById(R.id.txtSearchMode)
        tryAgain = findViewById(R.id.txtTryAgain)
        searchFragment = findViewById(R.id.searchFragment)
        viewpager = findViewById(R.id.viewpager)
        tablayout = findViewById(R.id.tablayout)


    }

    private fun applySearch() {
        searchText?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                visibilityMain()
                searchClick()

            }

            override fun afterTextChanged(s: Editable?) {
                visibility(s)
            }
        })
    }

    fun visibility(s: Editable?) {
        if (s != null) {
            if (s.isEmpty()) {
                    tablayout.visibility = View.VISIBLE
                    viewpager.visibility = View.VISIBLE
                    icon.visibility = View.GONE
                    searchMode.visibility = View.GONE
                    tryAgain.visibility = View.GONE
                    searchFragment.visibility = View.VISIBLE
                    searchText?.text?.clear()
            }
        }
    }

    fun searchClick() {
        Log.d("teste", searchText.toString())
        searchText?.setOnEditorActionListener { _, actionId, _ ->
            when (actionId) {
                EditorInfo.IME_ACTION_SEARCH -> {
                    movieSearchText = searchText?.text.toString()

                    if (fragment == null) {

                        fragment = SearchFragment.searchString(movieSearchText)
                        fragment?.let {
                            supportFragmentManager.beginTransaction()
                                .add(R.id.searchFragment, it)
                                .addToBackStack(null)
                                .commit()
                        }
                    } else {
                        fragment?.update(movieSearchText.toUri())

                    }
                    true
                }
                else -> false
            }
        }
    }

    fun visibilityMain(){
            tablayout.visibility = View.GONE
            viewpager.visibility = View.GONE
            icon.visibility = View.VISIBLE
            searchMode.visibility = View.VISIBLE
            tryAgain.visibility = View.VISIBLE
            searchFragment.visibility = View.VISIBLE
    }

    //Tablayout
    private fun getTabTitle(position: Int): String {
        return when (position) {
            0 -> "Todos os filmes"
            1 -> "Favoritos"
            else -> ""
        }
    }

}

