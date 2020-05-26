package com.example.starwars.presentation.home.showfilmedetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.starwars.R
import kotlinx.android.synthetic.main.activity_show_filme_detail.*

class ShowFilmeDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_filme_detail)
        val posicao = intent.getStringExtra("position")
        val titulo = intent.getStringExtra("tituloDoFilme")

        valor.text = posicao
        nomeFilme.text = titulo
    }
}
