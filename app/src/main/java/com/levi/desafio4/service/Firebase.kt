package com.levi.desafio4.service

import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.levi.desafio4.entity.User

class Firebase() : FirebaseInterface {

    lateinit var user: User
    var authUser: MutableLiveData<Boolean> = MutableLiveData()

    private fun getInstanceFirebase(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    override suspend fun authUser(email: String, password: String) {
        getInstanceFirebase().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                try {
                    if(task.isSuccessful) {
                        val firebaseUser: FirebaseUser = task.result?.user!!
                        authUser.value = true
                        user = User(firebaseUser.uid, firebaseUser.email.toString())
                    }
                } catch (e: Exception) {
                    authUser.value = false
                }
            }
    }

    override suspend fun registerUser(name: String, email: String, password: String) {
        getInstanceFirebase().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                try {
                    if(task.isSuccessful) {
                        val firebaseUser: FirebaseUser = task.result?.user!!
                        authUser.value = true
                        user = User(firebaseUser.uid, firebaseUser.email.toString())
                    }
                } catch (e: Exception) {
                    authUser.value = false
                }
            }
    }
}