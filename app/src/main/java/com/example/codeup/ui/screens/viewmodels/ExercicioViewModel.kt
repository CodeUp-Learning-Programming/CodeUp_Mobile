package com.example.codeup.ui.screens.viewmodels

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.codeup.api.RetrofitService
import com.example.codeup.util.StoreExercicio
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ExercicioViewModel(private val bearerToken: String?): ViewModel(){
//    val exerciciosAtuais = MutableLiveData<List<Exercicio>>() // Corrigido para o tipo List<Fase>
    val apiExercicio = RetrofitService.getApiExercicioService(bearerToken)
    val erroApi = MutableLiveData("")

    fun buscarExerciciosPorIdFase(idFase: Int, context: Context) {
        Log.e("Exercicios", "Iniciando Carregamento dos exercicios!")

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val storeExercicios = StoreExercicio.getInstance(context)
                val response = apiExercicio.buscarExerciciosPorIdFase(idFase)
                if (response.isSuccessful && response.body() != null) {
                    Log.e("Exercicios", "Exercicios carregados!")

                    val exerciciosResponse = response.body()
//                    exerciciosAtuais.value = exerciciosResponse ?: emptyList()
                    storeExercicios.saveExercicios(exerciciosResponse!!)
                } else {
                    Log.e("Exercicios", "Erro ao carregar os exercicios: ${response.message()}")
                    erroApi.postValue("Erro ao carregar os exercicios: ${response.message()}")
                }
            } catch (e: Exception) {
                Log.e("Exercicios", "Erro ao carregar os exercicios: ${e.message}")
                erroApi.postValue("Erro ao carregar os exercicios: ${e.message}")
            }
        }
    }
    fun testJavaScriptCode(funcao:String, idExercicio:Int, idFase:Int, context: Context) {
        Log.e("Exercicios", "Iniciando Carregamento dos exercicios!")

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val storeExercicios = StoreExercicio.getInstance(context)
                val response = apiExercicio.testJavaScriptCode(funcao, idExercicio, idFase)
                if (response.isSuccessful && response.body() != null) {
                    Log.e("Exercicios", "Exercicios testados!")
                    val exerciciosResponse = response.body()


                } else {
                    Log.e("Exercicios", "Erro ao carregar os exercicios: ${response.message()}")
                    erroApi.postValue("Erro ao carregar os exercicios: ${response.message()}")
                }
            } catch (e: Exception) {
                Log.e("Exercicios", "Erro ao carregar os exercicios: ${e.message}")
                erroApi.postValue("Erro ao carregar os exercicios: ${e.message}")
            }
        }
    }
}
