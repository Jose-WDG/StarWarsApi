package com.example.starwars.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.starwars.R
import com.example.starwars.infrastructure.Filme
import com.example.starwars.presentation.home.adapter.FilmeListener
import com.example.starwars.presentation.home.adapter.FilmesAdapter
import org.koin.android.viewmodel.ext.android.sharedViewModel
import java.util.*

class HomeFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private val viewModel: HomeViewModel by sharedViewModel()
    private lateinit var filmesAdapter: FilmesAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_filmes, container, false)
        configRecycle(root)
        configAdapter()

        viewModel.showDatas().observe(this, Observer {
            filmesAdapter.seTData(it)
            recyclerView.adapter = filmesAdapter
        })

        return root
    }

    private fun configAdapter() {
        filmesAdapter = FilmesAdapter(Arrays.asList())
        filmesAdapter.setLisstener(object : FilmeListener<Filme>{
            override fun listenerImpl(position: Int?, lista: List<Filme>?) {
                Toast.makeText(context,"Foi muleque "+position,Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun configRecycle(root: View) {
        recyclerView = root.findViewById(R.id.filmes_recyclerView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }
}
