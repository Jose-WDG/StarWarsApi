package com.example.starwars.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.starwars.R
import com.example.starwars.infrastructure.Filme
import com.example.starwars.presentation.home.adapter.FilmesAdapter
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    val viewModel: HomeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_filmes, container, false)
        configAdapter(root)
        return root
    }

    private fun configAdapter(root: View) {
        recyclerView = root.findViewById(R.id.filmes_recyclerView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        viewModel.showDatas().observe(this,
            Observer<List<Filme>> {
                recyclerView.adapter = FilmesAdapter(it)
            })
    }

}
