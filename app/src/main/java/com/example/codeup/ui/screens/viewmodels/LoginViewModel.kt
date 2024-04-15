//import android.content.Context
//import android.content.Intent
//import androidx.datastore.preferences.core.edit
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.example.codeup.api.RetrofitService
//import com.example.codeup.data.ItemAdquirido
//import com.example.codeup.data.Usuario
//import com.example.codeup.data.UsuarioLoginRequest
//import com.example.codeup.ui.screens.TelaHome
//import com.example.codeup.util.DataStoreUtil
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.asStateFlow
//import kotlinx.coroutines.launch
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//import androidx.datastore.preferences.core.preferencesKey
//
//
//object UserPreferencesKeys {
//    val ID = preferencesKey<Long>("user_id")
//    val NAME = preferencesKey<String>("user_name")
//    val EMAIL = preferencesKey<String>("user_email")
//}
//
//class LoginViewModel(context: Context) : ViewModel() {
//    private val dataStore = DataStoreUtil.provideDataStore(context)
//
//    // Use MutableStateFlow para representar o estado do usuário logado
//    private val _usuarioLogado = MutableStateFlow<Usuario?>(null)
//    val usuarioLogado = _usuarioLogado.asStateFlow()
//
//    fun fazerLogin(usuarioLoginRequest: UsuarioLoginRequest, lembrar: Boolean, context: Context) {
//
//        val ApiUsuarios = RetrofitService.getApiUsuarioService(null)
//        val post = ApiUsuarios.login(usuarioLoginRequest);
//
//        post.enqueue(object : Callback<Usuario> {
//            override fun onResponse(call: Call<Usuario>, response: Response<Usuario>) {
//                if (response.isSuccessful) {
//                    val usuarioResponse = response.body()
//                    if (usuarioResponse != null) {
//
//                        if(lembrar){
//                            salvarUsuarioNoDataStore(usuarioResponse)
//                        }
//
//                        val telaHome = Intent(context, TelaHome::class.java)
//                        telaHome.putExtra("usuario", usuarioResponse)
//                        context.startActivity(telaHome)
//                    }
//                }
//            }
//
//            override fun onFailure(call: Call<Usuario>, t: Throwable) {
//
//                if (usuarioLoginRequest.email == "admin" && usuarioLoginRequest.senha == "123") {
//                    val telaHome = Intent(context, TelaHome::class.java)
//                    telaHome.putExtra(
//                        "usuario", Usuario(
//                            id = 1,
//                            fotoPerfil = "", nome = "Administrador",
//                            email = "admin@sptech.school",
//                            token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkZXZAc3B0ZWNoLnNjaG9vbCIsImlhdCI6MTY5ODA4NDY1NCwiZXhwIjoxNzAxNjg0NjU0fQ._ByXuksiF9C2K2Xu5OrAhquC2SHNfiAO7uut0pGEXN8JKzY8bzGksmQJQ6ICZIJ3uhladvK7NoDJyeS7iMrA0A",
//                            moedas = 500,
//                            nivel = 950,
//                            xp = 250,
//                            itensAdquiridos = listOf(
//                                ItemAdquirido(
//                                    id = 1,
//                                    nomeItem = "Item 1",
//                                    fotoItem = "!",
//                                    tipoItem = "Imagem",
//                                    precoItem = 20.0,
//                                    descricaoItem = "Item 1",
//                                    equipado = false
//                                ),
//                                ItemAdquirido(
//                                    id = 2,
//                                    nomeItem = "Item 2",
//                                    fotoItem = "?",
//                                    tipoItem = "Imagem2",
//                                    precoItem = 20.0,
//                                    descricaoItem = "Item 2",
//                                    equipado = false
//                                )
//                            )
//
//
//                        )
//                    )
//                    context.startActivity(telaHome)
//                }
//            }
//        })
//    }
//
//    // Função para salvar o usuário no DataStore
//    private fun salvarUsuarioNoDataStore(usuario: Usuario) {
//        viewModelScope.launch {
//            dataStore.edit { preferences ->
//                preferences[UserPreferencesKeys.ID] = usuario.id
//                preferences[UserPreferencesKeys.NAME] = usuario.nome
//                preferences[UserPreferencesKeys.EMAIL] = usuario.email
//            }
//            // Atualizar o estado do usuário logado na ViewModel
//            _usuarioLogado.value = usuario
//        }
//    }
//}
