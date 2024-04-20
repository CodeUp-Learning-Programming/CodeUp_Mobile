package com.example.codeup.ui.screens.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.codeup.api.RetrofitService
import com.example.codeup.data.Fase
import kotlinx.coroutines.launch


class FaseViewModel(private val bearerToken: String?,private val idMateria: Int): ViewModel(){
    val faseAtivo = MutableLiveData<Fase>()
    val fases = MutableLiveData<List<Fase>>() // Corrigido para o tipo List<Fase>
    val apiFase = RetrofitService.getApiFaseService(bearerToken)
    val erroApi = MutableLiveData("")

    init {
        buscarFasePelaMateria(idMateria)
    }

    fun buscarFasePelaMateria(idMateria: Int) {
        viewModelScope.launch {
            try {
                val response = apiFase.buscarFasePelaMateria(idMateria)
                if (response.isSuccessful) {
                    fases.value = response.body() ?: emptyList()
                } else {
                    Log.e("FaseViewModel", "Erro ao carregar fases: ${response.message()}")
                    erroApi.postValue("Erro ao carregar fases: ${response.message()}")
                }
            } catch (e: Exception) {
                Log.e("FaseViewModel", "Erro ao carregar fases: ${e.message}")
                erroApi.postValue("Erro ao carregar fases: ${e.message}")
            }
        }
    }
}
