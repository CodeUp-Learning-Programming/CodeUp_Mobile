package com.example.codeup.data

import java.io.Serializable

data class Amigo(
    val nome: String,
    val email: String,
    val foto: String,
    val xp: Int
): Serializable
