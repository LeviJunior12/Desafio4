package com.levi.desafio4.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.levi.desafio4.R
import com.levi.desafio4.entity.Game
import com.levi.desafio4.service.databaseFirebase
import com.levi.desafio4.viewmodel.GameViewModel
import kotlinx.android.synthetic.main.register_game_body.view.*

class CadastreGameFragment : Fragment() {

    lateinit var nameGame: String
    lateinit var createAt: String
    lateinit var describe: String

    private val viewModel by viewModels<GameViewModel> {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return GameViewModel(databaseFirebase) as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_cadastre_game, container, false)
        addGame(view)
        return view
    }

    private fun addGame(view: View) {
        view.btnSaveGame.setOnClickListener {
            val game = getDataFields(view)
            val result = viewModel.saveGame("games", game)
        }

        findNavController().navigate(R.id.action_cadastreGameFragment_to_homeFragment)
    }

    private fun getDataFields(view: View): Game {
        nameGame = view.tfNameGame.text.toString()
        createAt = view.tfCreateAt.text.toString()
        describe = view.tfDescribe.text.toString()

        return Game(nameGame, createAt, describe)
    }

}