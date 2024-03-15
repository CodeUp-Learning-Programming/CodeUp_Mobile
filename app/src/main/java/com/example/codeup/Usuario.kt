package com.example.codeup

import java.io.Serializable

data class Usuario(
    var email:String = "",
    var senha:String = ""
): Serializable
