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
import com.example.codeup.ui.screens.TelaExercicio
import com.example.codeup.ui.screens.TelaHome
import com.example.codeup.ui.screens.TelaLogin
import com.example.codeup.util.StoreRememberUser
import com.example.codeup.util.StoreUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch


class UsuarioViewModel(private val bearerToken: String?) : ViewModel() {


    // Estado para carregar indicação
    var carregando = MutableLiveData(false)
    val loginStatus = MutableLiveData<String?>()

    val usuarioAtivo = MutableLiveData<Usuario>()
    val apiUsuario = RetrofitService.getApiUsuarioService(bearerToken)
    val erroApi = MutableLiveData<String?>()

    fun cadastrar(usuarioRegisterRequest: UsuarioRegisterRequest, context: Context) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = apiUsuario.cadastrar(usuarioRegisterRequest)
                if (response.isSuccessful) {
                    val telaLogin = Intent(context, TelaLogin::class.java)
                    context.startActivity(telaLogin)
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

    fun login(
        usuarioLoginRequest: UsuarioLoginRequest,
        context: Context,
        dataStoreRememberUser: StoreRememberUser,
        lembrar: Boolean
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            carregando.postValue(true)

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

                    loginStatus.postValue("Login bem-sucedido")
                    val telaHome = Intent(context, TelaHome::class.java)
                    context.startActivity(telaHome)

                } else {
                    loginStatus.postValue("Erro de login: ${usuarioResponse.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                loginStatus.postValue("Erro de conexão: ${e.message}")
            } finally {
                carregando.postValue(false)
            }
        }
    }


    fun atualizarPorId(id: Int, context: Context) {

        CoroutineScope(Dispatchers.IO).launch {
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
                Log.e("API", "Erro no delete de filmes: ${e.message}")
            }
        }
    }


    fun equiparItem(fotoItem: String, tipoItem: String, context: Context) {
        val storeUser = StoreUser.getInstance(context)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                if (tipoItem == "Imagem") {
                    val usuarioResponse = apiUsuario.atualizarFotoPerfil(FotoPerfilRequest(fotoItem))

                    if (usuarioResponse.isSuccessful) {
                        val currentUser =
                            storeUser.getUsuario.first() // Coleta apenas a última emissão do Flow
                        currentUser?.let { usuario ->
                            usuario.fotoPerfil = fotoItem
                            storeUser.saveUsuario(usuario) // Salva o usuário atualizado
                        }
                    } else {
                        Log.e("API", " erro no post")
                    }


                }

            } catch (e: Exception) {
                Log.e("API", "Erro no delete de filmes: ${e.message}")
            }
        }
    }

}
