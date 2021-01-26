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
import com.levi.desafio4.service.databaseFirebase
import com.levi.desafio4.viewmodel.GameViewModel
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment() {

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
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        addGame(view)
        getGames()
        return view
    }

    private fun getGames() {
        viewModel.getAllGame("games")
    }

    private fun addGame(view: View) {
        view.fbAddGame.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_cadastreGameFragment)
        }
    }

}