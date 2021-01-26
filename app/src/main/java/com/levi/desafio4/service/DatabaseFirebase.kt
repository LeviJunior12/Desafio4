package com.levi.desafio4.service

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.levi.desafio4.entity.Game

class DatabaseFirebase {

    lateinit var reference: DatabaseReference

    private fun getInstanceDatabase(): FirebaseDatabase {
        return FirebaseDatabase.getInstance()
    }

    fun referenceDatabase(reference: String) {
        this.reference = getInstanceDatabase().getReference(reference)
    }

    fun saveGame(table: String, game: Game): String {
        referenceDatabase(table)

        val result = reference
            .child(game.name)
            .setValue(game)

        return result.toString()
    }

}

val databaseFirebase: DatabaseFirebase = DatabaseFirebase()