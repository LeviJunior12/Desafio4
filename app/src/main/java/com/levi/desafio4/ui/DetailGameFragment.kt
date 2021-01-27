package com.levi.desafio4.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.levi.desafio4.R
import com.levi.desafio4.entity.Game
import kotlinx.android.synthetic.main.detail_game_body.view.*
import kotlinx.android.synthetic.main.fragment_detail_game.*
import kotlinx.android.synthetic.main.fragment_detail_game.view.*

class DetailGameFragment : Fragment() {

    lateinit var detailGame: Game

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detail_game, container, false)

        detailGame = arguments?.get("game") as Game
        setDataGame(view)
        backHome(view)

        return view
    }

    private fun backHome(view: View) {
        view.toolbar_icon.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setDataGame(view: View) {
        view.ivGameMain.setImageResource(R.drawable.splash_firebase)
        view.tvTitleGame.text = detailGame.name
        view.tvReleaseGame.text = detailGame.createdAt
        view.tvDescribeGame.text = detailGame.describe
    }

}