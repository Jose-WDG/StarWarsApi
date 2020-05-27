package com.example.starwars.presentation.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.starwars.R
import com.example.starwars.infrastructure.Filme
import com.example.starwars.presentation.home.adapter.FilmesAdapter
import com.example.starwars.presentation.home.adapter.HolderListener
import com.example.starwars.presentation.home.showfilmedetail.ShowFilmeDetailActivity
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.sharedViewModel

class HomeFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private val viewModel: HomeViewModel by sharedViewModel()
    private val filmesAdapter: FilmesAdapter by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_filmes, container, false)
        configRecycle(root)
        configAdapter()
        viewModel.showDatas().observe(this, Observer {
            filmesAdapter.seTData(it)
        })

        return root
    }

    private fun configAdapter() {
        filmesAdapter.setLisstener(object : HolderListener<Filme> {
            override fun listenerImpl(position: Int?, lista: List<Filme>?) {
                if (position != null && lista != null){
                    val intents = Intent(context, ShowFilmeDetailActivity::class.java)
                    intents.apply {
                        putExtra("position", position)
                        putExtra("filme",lista[position])
                    }
                    startActivity(intents)
                }
            }
        })
    }

    private fun configRecycle(root: View) {
        recyclerView = root.findViewById(R.id.filmes_recyclerView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = filmesAdapter
    }
}
