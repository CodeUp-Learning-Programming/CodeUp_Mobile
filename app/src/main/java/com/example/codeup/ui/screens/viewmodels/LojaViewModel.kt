package com.example.codeup.ui.screens.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.codeup.api.RetrofitService
import com.example.codeup.data.Loja
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class LojaViewModel(private val bearerToken: String): ViewModel(){
    val loja = MutableLiveData<Loja?>()
    val apiLoja = RetrofitService.getApiLojaService(bearerToken)
    val erroApi = MutableLiveData("")

    init {
        carregarLoja()
    }

    fun carregarLoja() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = apiLoja.buscarLojaCompleta()
                if (response.isSuccessful) {
                    val lojaResponse = response.body()
                    loja.postValue(lojaResponse)
                } else {
                    Log.e("api", "Erro ao carregar loja: ${response.message()}")
                    erroApi.postValue("Erro ao carregar loja: ${response.message()}")
                }
            } catch (e: Exception) {
                Log.e("api", "Erro ao carregar loja: ${e.message}")
                erroApi.postValue("Erro ao carregar loja: ${e.message}")
            }
        }
    }

    fun comprarItem(id:Int) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val comprar = apiLoja.comprarItem(id)
                if (comprar.isSuccessful) {
                    //atualizar o usuario e os itens adquiridos dele
                } else {
                    Log.e("api"," erro no delete")
                }
            } catch (e:Exception) {
                Log.e("api", "Erro no delete de filmes: ${e.message}")
            }
        }
    }
}
