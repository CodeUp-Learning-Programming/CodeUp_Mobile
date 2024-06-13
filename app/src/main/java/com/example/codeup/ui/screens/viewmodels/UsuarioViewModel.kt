package com.example.codeup.ui.screens.viewmodels

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.codeup.api.RetrofitService
import com.example.codeup.data.FotoPerfilRequest
import com.example.codeup.data.Usuario
import com.example.codeup.data.UsuarioLoginRequest
import com.example.codeup.data.UsuarioRegisterRequest
import com.example.codeup.ui.screens.TelaHome
import com.example.codeup.ui.screens.TelaLogin
import com.example.codeup.util.StoreRanking
import com.example.codeup.util.StoreRememberUser
import com.example.codeup.util.StoreUser
import com.example.codeup.util.StoreUserGraficoExercicio
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch


class UsuarioViewModel(private val bearerToken: String?) : ViewModel() {


    // Estado para carregar indicação
    val loginStatus = MutableLiveData<String?>()

    val usuarioAtivo = MutableLiveData<Usuario>()
    val apiUsuario = RetrofitService.getApiUsuarioService(bearerToken)
    val erroApi = MutableLiveData<String?>()

    fun cadastrar(usuarioRegisterRequest: UsuarioRegisterRequest, context: Context) {
        Log.d("USUARIO","Iniciando Cadastro")

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = apiUsuario.cadastrar(usuarioRegisterRequest)
                if (response.isSuccessful) {
                    Log.d("USUARIO","Cadastro bem-sucedido")
                    val telaLogin = Intent(context, TelaLogin::class.java)
                    context.startActivity(telaLogin)
                } else {
                    Log.d("USUARIO","Cadastro mal-sucedido")
                    erroApi.postValue("Erro ao realizar o cadastro: ${response.message()}")
                }
            } catch (e: Exception) {
                Log.d("USUARIO","Erro de conexão")
                loginStatus.postValue("Erro de conexão: ${e.message}")
            }
        }
    }

    fun login(
        usuarioLoginRequest: UsuarioLoginRequest,
        context: Context,
        dataStoreRememberUser: StoreRememberUser,
        lembrar: Boolean
    ) {
        Log.d("USUARIO","Iniciando Login")
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val storeUser = StoreUser.getInstance(context)
                val usuarioResponse = apiUsuario.login(usuarioLoginRequest)

                if (usuarioResponse.isSuccessful && usuarioResponse.body() != null) {

                    val usuario = usuarioResponse.body()!!
                    usuarioAtivo.postValue(usuario)
                    storeUser.saveUsuario(usuario)

                    if (lembrar) {
                        dataStoreRememberUser.saveEmail(usuarioLoginRequest.email)
                        dataStoreRememberUser.savePassword(usuarioLoginRequest.senha)
                    }
                    Log.d("USUARIO","Login bem-sucedido")
                    loginStatus.postValue("Login bem-sucedido")
                    val telaHome = Intent(context, TelaHome::class.java)
                    context.startActivity(telaHome)


                } else {
                    Log.d("USUARIO","Login mal-sucedido")
                    loginStatus.postValue("Erro de login: ${usuarioResponse.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                Log.d("USUARIO","Erro de conexão")
                loginStatus.postValue("Erro de conexão: ${e.message}")
            }
        }
    }


    fun atualizarPorId(id: Int, context: Context) {

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val storeUser = StoreUser.getInstance(context)
                val usuarioResponse = apiUsuario.buscarPorId(id)

                if (usuarioResponse.isSuccessful && usuarioResponse.body() != null) {
                    val usuario = usuarioResponse.body()!!
                    usuarioAtivo.postValue(usuario)
                    storeUser.saveUsuario(usuario)
                } else {
                    Log.e("API", " erro no post")
                }
            } catch (e: Exception) {
                Log.d("USUARIO","Erro de conexão: ${e.message}")
            }
        }
    }


    fun equiparItem(fotoItem: String, tipoItem: String, context: Context) {
        val storeUser = StoreUser.getInstance(context)
        Log.d("USUARIO","Equipando item")

        viewModelScope.launch(Dispatchers.IO) {
            try {
                if (tipoItem == "Foto de Perfil") {
                    val usuarioResponse = apiUsuario.atualizarFotoPerfil(FotoPerfilRequest(fotoItem))

                    if (usuarioResponse.isSuccessful) {
                        Log.d("USUARIO","Item equipado com sucesso")
                        val currentUser =
                            storeUser.getUsuario.first() // Coleta apenas a última emissão do Flow
                        currentUser?.let { usuario ->
                            usuario.fotoPerfil = fotoItem
                            storeUser.saveUsuario(usuario) // Salva o usuário atualizado
                        }
                    } else {
                        Log.d("USUARIO","Não foi possivel equipar o item")
                    }
                }
            } catch (e: Exception) {
                Log.e("C", "Erro de conexão: ${e.message}")
            }
        }
    }

    fun ranking(context: Context) {

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val storeRanking = StoreRanking.getInstance(context)
                val usuarioResponse = apiUsuario.ranking()

                if (usuarioResponse.isSuccessful && usuarioResponse.body() != null) {
                    val response = usuarioResponse.body()!!
                    storeRanking.saveRanking(response)
                   // storeRanking.saveRankingUsuarioAtual(response)
                    Log.e("USUARIO", response.toString())

                } else {
                    Log.e("API", " erro no post")
                }
            } catch (e: Exception) {
                Log.d("USUARIO","Erro de conexão: ${e.message}")
            }
        }
    }
    fun buscarExerciciosPorMes(id: Int, context: Context) {

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val storeUserGraficoExercicio = StoreUserGraficoExercicio.getInstance(context)
                val usuarioResponse = apiUsuario.buscarExerciciosPorMes(id)

                if (usuarioResponse.isSuccessful && usuarioResponse.body() != null) {
                    val usuario = usuarioResponse.body()!!
                    storeUserGraficoExercicio.saveListExercicios(usuario)
                    Log.e("USUARIO", usuario.toString())

                } else {
                    Log.e("API", " erro no post")
                }
            } catch (e: Exception) {
                Log.d("USUARIO","Erro de conexão: ${e.message}")
            }
        }
    }
}
