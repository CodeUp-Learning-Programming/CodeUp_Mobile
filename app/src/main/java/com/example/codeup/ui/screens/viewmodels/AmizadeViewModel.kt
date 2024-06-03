package com.example.codeup.ui.screens.viewmodels

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.codeup.api.RetrofitService
import com.example.codeup.data.BuscarPorNomeRequest
import com.example.codeup.data.RespostaSolicitacao
import com.example.codeup.data.SolicitarAmizadeRequest
import com.example.codeup.util.StoreAmizades
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class AmizadeViewModel(private val bearerToken: String?) : ViewModel() {

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
            }
        }
    }

    fun solicitacoesRecebidas(idUsuario: Int, context: Context) {
        Log.d("AMIZADE", "Enviando solicitação de amizade")
        viewModelScope.launch(Dispatchers.IO) {
            val response = apiAmizade.solicitacoesAmizadeRecebidas(idUsuario)
            val storeAmizades = StoreAmizades.getInstance(context)

            try {
                if (response.isSuccessful) {
                    Log.d("AMIZADE", "Lista de pedidos de amizades recebida com sucesso!")
                    storeAmizades.savePedidosAmizade(response.body()!!)

                } else {
                    Log.d("AMIZADE", "Lista de pedidos de amizades recebida com sucesso!")
                    erroApi.postValue("Erro ao realizar o cadastro: ${response.message()}")
                }
            } catch (e: Exception) {
                Log.d("AMIZADE", "Erro de conexão")
            }
        }
    }

    fun solicitacoesEnviadas(idUsuario: Int, context: Context) {
        Log.d("AMIZADE", "Enviando solicitação de amizade")
        viewModelScope.launch(Dispatchers.IO) {
            val response = apiAmizade.solicitacoesAmizadeEnviadas(idUsuario)
            val storeAmizades = StoreAmizades.getInstance(context)

            try {
                if (response.isSuccessful) {
                    Log.d("AMIZADE", "Solicitação enviada com sucesso")
//                    storeAmizades.saveAmigos(response.body()!!)

                } else {
                    Log.d("AMIZADE", "Cadastro mal-sucedido")
                    erroApi.postValue("Erro ao realizar o cadastro: ${response.message()}")
                }
            } catch (e: Exception) {
                Log.d("AMIZADE", "Erro de conexão")
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
            }
        }
    }

    fun gerenciarConvite(respostaSolicitacao: RespostaSolicitacao, context: Context) {
        Log.d("AMIZADE", "Buscando lista de amigos")
        viewModelScope.launch(Dispatchers.IO) {
            val response = apiAmizade.gerenciarConvite(respostaSolicitacao)
            try {
                if (response.isSuccessful) {
                    Log.d("AMIZADE", "Pedido gerenciado com sucesso!")
                    if(respostaSolicitacao.resposta){
                        Log.d("AMIZADE", "Pedido aceito com sucesso!")
                    }else{
                        Log.d("AMIZADE", "Pedido recusado com sucesso!")
                    }
                } else {
                    Log.d("AMIZADE", "Erro ao obter a lista")
                    erroApi.postValue("Erro ao realizar o cadastro: ${response.message()}")
                }
            } catch (e: Exception) {
                Log.d("AMIZADE", "Erro de conexão")
            }

        }
    }

    fun buscarRelaciomento(buscarPorNome: BuscarPorNomeRequest, context: Context) {
        Log.d("AMIZADE", "Buscando lista de amigos")
        viewModelScope.launch(Dispatchers.IO) {
            val response = apiAmizade.buscarRelacionamento(buscarPorNome.idUsuario, buscarPorNome.nome)
            val storeAmizades = StoreAmizades.getInstance(context)

            try {
                if (response.isSuccessful) {
                    Log.d("AMIZADE", "Lista adquirida com sucesso")
                    storeAmizades.saveListaBuscarAmigos(response.body()!!)
                } else {
                    Log.d("AMIZADE", "Erro ao obter a lista")
                    erroApi.postValue("Erro ao realizar o cadastro: ${response.message()}")
                }
            } catch (e: Exception) {
                Log.d("AMIZADE", "Erro de conexão")
            }
        }
    }

}
