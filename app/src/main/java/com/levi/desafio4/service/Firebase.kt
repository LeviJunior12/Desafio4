package com.levi.desafio4.service

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class Firebase() : FirebaseInterface {

    private fun getInstanceFirebase(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    override suspend fun authUser(email: String, password: String) {
        getInstanceFirebase().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                try {
                    val firebaseUser: FirebaseUser = task.result?.user!!
                    Log.i("LOGIN USER", "LOGADO COM SUCESSO")
                } catch (e: Exception) {
                    Log.i("ERROR LOGIN USER", e.message.toString())
                }
            }
    }

    override suspend fun registerUser(name: String, email: String, password: String) {
        getInstanceFirebase().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                try {
                    val firebaseUser: FirebaseUser = task.result?.user!!
                    Log.i("REGISTER USER", "USER REGISTER")
                } catch (e: Exception) {
                    Log.i("ERROR REGISTER", task.exception?.message.toString())
                }
            }
    }
}