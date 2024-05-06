package com.example.codeup.ui.screens.viewmodels

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.codeup.api.RetrofitService
import com.example.codeup.data.SolicitarAmizadeRequest
import com.example.codeup.data.Usuario
import com.example.codeup.util.StoreAmizades
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class AmizadeViewModel(private val bearerToken: String?) : ViewModel() {


    // Estado para carregar indicação
    var carregando = MutableLiveData(false)
    val loginStatus = MutableLiveData<String?>()

    val usuarioAtivo = MutableLiveData<Usuario>()
    val apiAmizade = RetrofitService.getApiAmizadeService(bearerToken)

    val erroApi = MutableLiveData<String?>()

    fun solicitarAmizade(solicitarAmizadeRequest: SolicitarAmizadeRequest) {
        Log.d("AMIZADE", "Enviando solicitação de amizade")
        viewModelScope.launch(Dispatchers.IO) {
            val response = apiAmizade.solicitarAmizade(solicitarAmizadeRequest)

            try {
                if (response.isSuccessful) {
                    Log.d("AMIZADE", "Solicitação enviada com sucesso")

                } else {
                    Log.d("AMIZADE", "Cadastro mal-sucedido")
                    erroApi.postValue("Erro ao realizar o cadastro: ${response.message()}")
                }
            } catch (e: Exception) {
                Log.d("AMIZADE", "Erro de conexão")
                loginStatus.postValue("Erro de conexão: ${e.message}")
            }
        }
    }

    fun solicitacoesRecebidas(idUsuario: Int, context: Context) {
        Log.d("AMIZADE", "Enviando solicitação de amizade")
        viewModelScope.launch(Dispatchers.IO) {
            val response = apiAmizade.solicitacoesRecebidas(idUsuario)

            try {
                if (response.isSuccessful) {
                    Log.d("AMIZADE", "Solicitação enviada com sucesso")

                } else {
                    Log.d("AMIZADE", "Cadastro mal-sucedido")
                    erroApi.postValue("Erro ao realizar o cadastro: ${response.message()}")
                }
            } catch (e: Exception) {
                Log.d("AMIZADE", "Erro de conexão")
                loginStatus.postValue("Erro de conexão: ${e.message}")
            }
        }
    }

    fun solicitacoesEnviadas(idUsuario: Int, context: Context) {
        Log.d("AMIZADE", "Enviando solicitação de amizade")
        viewModelScope.launch(Dispatchers.IO) {
            val response = apiAmizade.solicitacoesEnviadas(idUsuario)

            try {
                if (response.isSuccessful) {
                    Log.d("AMIZADE", "Solicitação enviada com sucesso")

                } else {
                    Log.d("AMIZADE", "Cadastro mal-sucedido")
                    erroApi.postValue("Erro ao realizar o cadastro: ${response.message()}")
                }
            } catch (e: Exception) {
                Log.d("AMIZADE", "Erro de conexão")
                loginStatus.postValue("Erro de conexão: ${e.message}")
            }
        }
    }

    fun listarAmigos(idUsuario: Int, context: Context) {
        Log.d("AMIZADE", "Buscando lista de amigos")
        viewModelScope.launch(Dispatchers.IO) {
            val response = apiAmizade.listarAmigos(idUsuario)
            val storeAmizades = StoreAmizades.getInstance(context)

            try {
                if (response.isSuccessful) {
                    Log.d("AMIZADE", "Lista adquirida com sucesso")
                    storeAmizades.saveAmigos(response.body()!!)

                } else {
                    Log.d("AMIZADE", "Erro ao obter a lista")
                    erroApi.postValue("Erro ao realizar o cadastro: ${response.message()}")
                }
            } catch (e: Exception) {
                Log.d("AMIZADE", "Erro de conexão")
                loginStatus.postValue("Erro de conexão: ${e.message}")
            }
        }
    }

}
