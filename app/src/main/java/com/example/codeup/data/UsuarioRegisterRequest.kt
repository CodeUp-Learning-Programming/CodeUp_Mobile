package com.example.codeup.data

import java.time.LocalDate

data class UsuarioRegisterRequest(
    var nome: String = "",
    var dtNascimento: LocalDate = LocalDate.now(),
    var email:String = "",
    var senha:String = "",
)
