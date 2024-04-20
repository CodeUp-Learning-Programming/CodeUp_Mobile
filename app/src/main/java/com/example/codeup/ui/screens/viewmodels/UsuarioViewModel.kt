package com.example.codeup.ui.screens.viewmodels

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.codeup.api.RetrofitService
import com.example.codeup.data.Usuario
import com.example.codeup.data.UsuarioLoginRequest
import com.example.codeup.data.UsuarioRegisterRequest
import com.example.codeup.ui.screens.TelaHome
import com.example.codeup.util.StoreRememberUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class UsuarioViewModel(private val bearerToken: String?): ViewModel(){

    val usuarioAtivo = MutableLiveData<Usuario>()
    val apiUsuario = RetrofitService.getApiUsuarioService(bearerToken)
    val erroApi = MutableLiveData("")

    fun cadastrar(usuarioRegisterRequest: UsuarioRegisterRequest) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = apiUsuario.cadastrar(usuarioRegisterRequest)
                if (response.isSuccessful) {

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

    fun login(usuarioLoginRequest: UsuarioLoginRequest, context: Context, scope: CoroutineScope, dataStoreRememberUser: StoreRememberUser, lembrar:Boolean) {

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val usuarioResponse = apiUsuario.login(usuarioLoginRequest)

                if (usuarioResponse.isSuccessful) {
                    usuarioAtivo.postValue(usuarioResponse.body())
                    Log.e("LEMBRAR","$lembrar")

                    if(lembrar){
                        scope.launch {
                            dataStoreRememberUser.saveEmail(usuarioLoginRequest.email)
                            dataStoreRememberUser.savePassword(usuarioLoginRequest.senha)
                        }
                    }
                    val telaHome = Intent(context, TelaHome::class.java)
                    telaHome.putExtra("usuario", usuarioResponse.body())
                    context.startActivity(telaHome)
                } else {
                    Log.e("API"," erro no post")
                }
            } catch (e:Exception) {
                Log.e("API", "Erro no delete de filmes: ${e.message}")
            }
        }
    }
}
