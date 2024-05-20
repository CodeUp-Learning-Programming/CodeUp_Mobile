package com.example.codeup.data

import java.io.Serializable

data class AmizadeRequestReceived(
    val nome: String,
    val email: String,
    val foto: String,
    val status: String
) : Serializable

