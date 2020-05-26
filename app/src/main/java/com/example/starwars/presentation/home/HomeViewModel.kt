package com.example.starwars.presentation.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.starwars.api.WebService
import com.example.starwars.infrastructure.Filme
import com.example.starwars.infrastructure.Results
import retrofit2.Call
import retrofit2.Response

class HomeViewModel : ViewModel() {
    private var mutableLiveData:MutableLiveData<List<Filme>>? = null

    private fun requestData(){
           WebService.getInstance()?.let {
               it.createService()
                   .requestFilms()
                   .enqueue(object : retrofit2.Callback<Results> {
                       override fun onFailure(call: Call<Results>, t: Throwable) {
                           Log.d("Erro>","Erro na requisição: "+t.message)
                       }

                       override fun onResponse(call: Call<Results>, response: Response<Results>) {
                           response.body()?.let { setData(it.resultados) }
                       }
                   })
           }
    }

    fun showDatas():LiveData<List<Filme>>{
        if(mutableLiveData == null){
            mutableLiveData = MutableLiveData<List<Filme>>()
            requestData()
        }
      return mutableLiveData as LiveData<List<Filme>>
    }

    private fun setData(filems: List<Filme>){
        mutableLiveData?.postValue(filems)
    }
}