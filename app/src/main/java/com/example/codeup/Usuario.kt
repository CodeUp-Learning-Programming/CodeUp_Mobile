package com.example.codeup

import java.io.Serializable

data class Usuario(
    var id:Int? = null,
    var email:String = "",
    var senha:String = "",
    var nome:String = "",
    var dataNascimento:String = ""
): Serializable
