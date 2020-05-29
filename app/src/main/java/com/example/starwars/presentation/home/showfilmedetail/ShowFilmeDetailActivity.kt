package com.example.starwars.presentation.home.showfilmedetail

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.starwars.R
import com.example.starwars.api.WebService
import com.example.starwars.infrastructure.Filme
import com.example.starwars.presentation.tooltip.Tooltip
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
        val tooltip = Tooltip.Builder(testeTooltip)
            .setGravity(Gravity.BOTTOM)
            .setPadding(40)
            .setTextSize(14F)
            .setBackgroundColor(ContextCompat.getColor(this,R.color.itau_tooltip_blue))
            .setTextColor(Color.WHITE)
            .setText("Visualize seu extrato até 90 dias")
            .setCornerRadius(8F)
            .setCancelable(true)
            .build()
        tooltip.show()

        testeTooltip.setOnClickListener {
            tooltip.show()
        }


    }
}
