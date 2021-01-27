package com.levi.desafio4.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.levi.desafio4.R
import com.levi.desafio4.adapter.HomeAdapter
import com.levi.desafio4.adapter.onClickListenerGame
import com.levi.desafio4.service.databaseFirebase
import com.levi.desafio4.viewmodel.GameViewModel
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment(), onClickListenerGame {

    lateinit var gridLayoutManager: GridLayoutManager
    lateinit var homeAdapter: HomeAdapter

    private val viewModel by viewModels<GameViewModel> {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return GameViewModel(databaseFirebase) as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getAllGame("games")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        addGame(view)
        getGames(view)

        return view
    }

    private fun getGames(view: View) {
        viewModel.listGame.observe(viewLifecycleOwner) {
            homeAdapter = HomeAdapter(it, this)
            view.rvGame.adapter = homeAdapter
        }

        val recyclerView = view.rvGame
        gridLayoutManager = GridLayoutManager(context, 2, LinearLayoutManager.VERTICAL, false)

        recyclerView.layoutManager = gridLayoutManager
        recyclerView.setHasFixedSize(true)

    }

    private fun addGame(view: View) {
        view.fbAddGame.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_cadastreGameFragment)
        }
    }

    override fun gameClick(position: Int) {
        viewModel.listGame.observe(this, {
            val game = it[position]

            var bundle = bundleOf("game" to game)
            findNavController().navigate(R.id.action_homeFragment_to_detailGameFragment, bundle)
        })
    }

}