package com.example.codeup.api

import com.example.codeup.api.controller.ExercicioApi
import com.example.codeup.api.controller.FaseApi
import com.example.codeup.api.controller.LojaApi
import com.example.codeup.api.controller.MateriaApi
import com.example.codeup.api.controller.UsuarioApi
import com.google.gson.GsonBuilder
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

    private val okHttpClient: OkHttpClient by lazy {
        val builder = OkHttpClient.Builder()
        builder.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        builder.build()
    }

    private fun <T> createApiService(apiClass: Class<T>, token: String? = null): T {
        val client = if (token != null) {
            okHttpClient.newBuilder().addInterceptor { chain ->
                val originalRequest = chain.request()
                val authenticatedRequest = originalRequest.newBuilder()
                    .header("Authorization", "Bearer $token")
                    .build()
                chain.proceed(authenticatedRequest)
            }.build()
        } else {
            okHttpClient
        }

        var gson = GsonBuilder()
            .disableHtmlEscaping()
            .create()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
            .create(apiClass)
    }
}
