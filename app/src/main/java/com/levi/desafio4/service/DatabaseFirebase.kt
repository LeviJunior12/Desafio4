package com.levi.desafio4.service

import android.util.Log
import com.google.firebase.database.*
import com.google.gson.Gson
import com.levi.desafio4.entity.Game

class DatabaseFirebase {

    lateinit var reference: DatabaseReference
    var listGames = ArrayList<Game>()

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

    fun getAllGame(reference: String): ArrayList<Game> {
        referenceDatabase(reference)

        this.reference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                listGames.clear()
                snapshot.children.forEach {
                    val game = Gson().fromJson(it.value.toString(), Game::class.java)
                    listGames.add(game)
                    Log.i("LIST GAMES", game.toString())
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

        return listGames
    }

}

val databaseFirebase: DatabaseFirebase = DatabaseFirebase()