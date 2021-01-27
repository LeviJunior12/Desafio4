package com.levi.desafio4.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.levi.desafio4.service.Firebase
import kotlinx.coroutines.launch

class AuthViewModel(val firebase: Firebase): ViewModel() {

    var userLogin: MutableLiveData<Boolean> = firebase.authUser

    fun loginUser(email: String, password: String) {
        viewModelScope.launch {
            firebase.authUser(email, password)
        }
    }

    fun registerUser(name: String, email: String, password: String) {
        viewModelScope.launch {
            firebase.registerUser(name, email, password)
        }
    }

}