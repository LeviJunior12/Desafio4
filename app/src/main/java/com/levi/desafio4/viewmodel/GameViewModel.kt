package com.levi.desafio4.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.levi.desafio4.entity.Game
import com.levi.desafio4.service.DatabaseFirebase
import kotlinx.coroutines.launch

class GameViewModel(val gameFirebase: DatabaseFirebase): ViewModel() {
    fun saveGame(table: String, game: Game){
        viewModelScope.launch {
            gameFirebase.saveGame(table, game)
        }
    }

    fun getAllGame(table: String) {
        viewModelScope.launch {
            gameFirebase.getAllGame(table)
        }
    }
}