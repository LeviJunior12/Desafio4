package com.levi.desafio4.viewmodel

import android.content.Intent
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.levi.desafio4.entity.Game
import com.levi.desafio4.service.DatabaseFirebase
import com.levi.desafio4.service.storageFirebase
import kotlinx.coroutines.launch

class GameViewModel(val gameFirebase: DatabaseFirebase): ViewModel() {

    var listGame = MutableLiveData<ArrayList<Game>>()
    var urlImage: MutableLiveData<String> = storageFirebase.urlImage

    fun saveGame(table: String, game: Game){
        viewModelScope.launch {
            gameFirebase.saveGame(table, game)
        }
    }

    fun getAllGame(table: String) {
        viewModelScope.launch {
            listGame.value = gameFirebase.getAllGame(table)
            Log.i("VIEW MODEL", listGame.toString())
        }
    }

    fun uploadImage(data: Intent, location: String) {
        viewModelScope.launch {
            storageFirebase.uploadImage(data, location)
        }
    }
}