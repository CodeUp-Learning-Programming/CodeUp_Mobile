package com.example.codeup.api

import com.example.codeup.api.controller.ExercicioApi
import com.example.codeup.api.controller.FaseApi
import com.example.codeup.api.controller.LojaApi
import com.example.codeup.api.controller.MateriaApi
import com.example.codeup.api.controller.UsuarioApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {

    //    private const val BASE_URL = "http://34.238.58.124/"
    private const val BASE_URL = "http://192.168.0.113:8080/"

    fun getApiExercicioService(token: String?): ExercicioApi {
        return createApiService(ExercicioApi::class.java, token)
    }

    fun getApiFaseService(token: String?): FaseApi {
        return createApiService(FaseApi::class.java, token)
    }

    fun getApiLojaService(token: String?): LojaApi {
        return createApiService(LojaApi::class.java, token)
    }

    fun getApiMateriaService(token: String?): MateriaApi {
        return createApiService(MateriaApi::class.java, token)
    }

    fun getApiUsuarioService(token: String?): UsuarioApi {
        return createApiService(UsuarioApi::class.java, token)
    }

    private inline fun <reified T> createApiService(apiClass: Class<*>, token: String? = null): T {
        val httpClient = OkHttpClient.Builder()
        if (token != null) {
            httpClient.addInterceptor { chain ->
                val originalRequest = chain.request()
                val authenticatedRequest = originalRequest.newBuilder()
                    .header("Authorization", "Bearer $token")
                    .build()
                chain.proceed(authenticatedRequest)
            }
        }
        httpClient.addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build()
        return retrofit.create(T::class.java)
    }
}
