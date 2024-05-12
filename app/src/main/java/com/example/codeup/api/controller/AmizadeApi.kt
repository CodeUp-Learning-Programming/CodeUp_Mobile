package com.example.codeup.api.controller

import com.example.codeup.data.Amigo
import com.example.codeup.data.AmizadeResult
import com.example.codeup.data.BuscarPorNome
import com.example.codeup.data.BuscarPorNomeResult
import com.example.codeup.data.RespostaSolicitacao
import com.example.codeup.data.SolicitarAmizadeRequest
import com.example.codeup.data.StatusPedidoAmizade
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AmizadeApi {
        @POST("amizades/solicitar/amizade")
        suspend fun solicitarAmizade(@Body solicitarAmizadeRequest: SolicitarAmizadeRequest): Response<Boolean>
        @GET("amizades/solicitacoes/recebidas")
        suspend fun solicitacoesAmizadeRecebidas(@Query("idUsuario") idUsuario: Int): Response<List<AmizadeResult>>
        @GET("amizades/solicitacoes/enviadas")
        suspend fun solicitacoesAmizadeEnviadas(@Query("idUsuario") idUsuario: Int): Response<List<AmizadeResult>>
        @GET("amizades/amigos")
        suspend fun listarAmigos(@Query("idUsuario") idUsuario: Int): Response<List<Amigo>>

        @POST("amizades/gerenciar/convite")
        suspend fun gerenciarConvite(@Body respostaSolicitacao: RespostaSolicitacao): Response<StatusPedidoAmizade>

        @POST("amizades/buscar_por_nome")
        suspend fun buscarRelaciomento(@Body buscarPorNome: BuscarPorNome): Response<BuscarPorNomeResult>



}