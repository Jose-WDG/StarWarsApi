package com.example.starwars.presentation.home.showfilmedetail

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.starwars.R
import com.example.starwars.api.WebService
import com.example.starwars.infrastructure.Filme
import com.example.starwars.infrastructure.Results
import kotlinx.android.synthetic.main.activity_show_filme_detail.*
import retrofit2.Call
import retrofit2.Response

class ShowFilmeDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_filme_detail)

        val posicao = (intent.getIntExtra("position",0))+1
        WebService.getInstance()?.let {
            it.createService()
                .requestFilmsForPosition(posicao.toString())
                .enqueue(object : retrofit2.Callback<Filme> {
                    override fun onFailure(call: Call<Filme>, t: Throwable) {
                        Log.d("Erro>","Erro na requisição: "+t.message)
                    }

                    override fun onResponse(call: Call<Filme>, response: Response<Filme>) {
                        valor.text = posicao.toString()
                        nomeFilme.text =  response.body()?.title
                    }

                })
        }

    }
}
