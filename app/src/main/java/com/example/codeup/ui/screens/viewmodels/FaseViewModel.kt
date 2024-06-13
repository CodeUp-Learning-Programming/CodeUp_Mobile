package com.example.codeup.ui.screens.viewmodels

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.codeup.api.RetrofitService
import com.example.codeup.util.StoreFase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class FaseViewModel(private val bearerToken: String?): ViewModel(){
//    val fases = MutableLiveData<List<Fase>>() // Corrigido para o tipo List<Fase>
    val apiFase = RetrofitService.getApiFaseService(bearerToken)
    val erroApi = MutableLiveData("")

    fun buscarFasePelaMateria(idMateria: Int, context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val storeFases = StoreFase.getInstance(context)

                val response = apiFase.buscarFasePelaMateria(idMateria)
                if (response.isSuccessful && response.body() != null) {
                    val faseResponse = response.body()
//                    fases.value = faseResponse ?: emptyList()
                    storeFases.saveFases(faseResponse!!)
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
