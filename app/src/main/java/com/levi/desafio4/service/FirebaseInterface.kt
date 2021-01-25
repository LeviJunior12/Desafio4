package com.levi.desafio4.service

interface FirebaseInterface {
    suspend fun authUser(email: String, password: String)
    suspend fun registerUser(name: String, email: String, password: String)
}

val firebase: Firebase = Firebase()