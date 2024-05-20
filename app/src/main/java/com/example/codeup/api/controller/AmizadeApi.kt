package com.example.codeup.api.controller

import com.example.codeup.data.Amigo
import com.example.codeup.data.AmizadeRequestReceived
import com.example.codeup.data.AmizadeResult
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
        suspend fun solicitacoesAmizadeRecebidas(@Query("idUsuario") idUsuario: Int): Response<List<AmizadeRequestReceived>>
        @GET("amizades/solicitacoes/enviadas")
        suspend fun solicitacoesAmizadeEnviadas(@Query("idUsuario") idUsuario: Int): Response<List<AmizadeResult>>
        @GET("amizades/amigos")
        suspend fun listarAmigos(@Query("idUsuario") idUsuario: Int): Response<List<Amigo>>

        @POST("amizades/gerenciar/convite")
        suspend fun gerenciarConvite(@Body respostaSolicitacao: RespostaSolicitacao): Response<StatusPedidoAmizade>

        @GET("amizades/busca-nome")
        suspend fun buscarRelacionamento(
                @Query("usuarioLogadoID", encoded = true) usuarioLogadoID: Int, @Query("nomePesquisa",
                        encoded = true
                ) nomePesquisa: String
        ): Response<List<BuscarPorNomeResult>>


}