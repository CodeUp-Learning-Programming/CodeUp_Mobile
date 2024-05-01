package com.example.codeup.ui.screens.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.codeup.api.RetrofitService
import com.example.codeup.data.Materia
import kotlinx.coroutines.launch


class MateriaViewModel(private val bearerToken: String?,private val idMateria: Int): ViewModel(){
    val materiaAtivo = MutableLiveData<Materia>()
    val materias = MutableLiveData<List<Materia>>()
    val apiMateria = RetrofitService.getApiMateriaService(bearerToken)
    val erroApi = MutableLiveData("")

    init {
        buscarMateria()
    }

    fun buscarMateria() {
        viewModelScope.launch {
            try {
                val response = apiMateria.listar()
                if (response.isSuccessful) {
                    materias.value = response.body() ?: emptyList()
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
