package com.example.codeup.ui.screens.viewmodels

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.codeup.api.RetrofitService
import com.example.codeup.data.Loja
import com.example.codeup.util.StoreLoja
import com.example.codeup.util.StoreUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch


class LojaViewModel(private val bearerToken: String): ViewModel(){
    val loja = MutableLiveData<Loja?>()
    val apiLoja = RetrofitService.getApiLojaService(bearerToken)
    val erroApi = MutableLiveData("")

    fun carregarLoja(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            Log.d("Loja","Carregando loja")

            try {
                val storeLoja = StoreLoja.getInstance(context)
                val response = apiLoja.buscarLojaCompleta()

                if (response.isSuccessful && response.body() != null) {
                    val lojaResponse = response.body()

                    loja.postValue(lojaResponse)
                    storeLoja.saveLoja(lojaResponse!!)
                    Log.d("Loja","Loja carregada com sucesso")

                } else {
                    Log.e("Loja", "Erro ao carregar loja: ${response.message()}")
                    erroApi.postValue("Erro ao carregar loja: ${response.message()}")
                }
            } catch (e: Exception) {
                Log.e("Loja", "Erro ao carregar loja: ${e.message}")
                erroApi.postValue("Erro ao carregar loja: ${e.message}")
            }
        }
    }

    fun comprarItem(id: Int, context: Context) {
        val storeUser = StoreUser.getInstance(context)

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = apiLoja.comprarItem(id)
                if (response.isSuccessful) {
                    val usuarioAtualizado = response.body() ?: return@launch // Se body for null, sair

                    val currentUser = storeUser.getUsuario.first() // Coleta apenas a última emissão do Flow
                    currentUser?.let { usuario ->
                        usuario.moedas = usuarioAtualizado.moedas
                        usuario.nivel = usuarioAtualizado.nivel
                        usuario.xp = usuarioAtualizado.xp
                        usuario.itensAdquiridos = usuarioAtualizado.itensAdquiridos ?: usuario.itensAdquiridos

                        storeUser.saveUsuario(usuario) // Salva o usuário atualizado
                    }
                   carregarLoja(context = context)


                } else {
                    Log.e("API", "Erro ao comprar item: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                Log.e("API", "Erro ao comprar item: ${e.message}")
            }
        }
    }
}
