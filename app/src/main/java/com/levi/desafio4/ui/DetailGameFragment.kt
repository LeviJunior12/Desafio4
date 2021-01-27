package com.levi.desafio4.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.levi.desafio4.R
import com.levi.desafio4.entity.Game
import kotlinx.android.synthetic.main.detail_game_body.view.*

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

        return view
    }

    private fun setDataGame(view: View) {
        view.ivGameMain.setImageResource(R.drawable.splash_firebase)
        view.tvTitleGame.text = detailGame.name
        view.tvReleaseGame.text = detailGame.createdAt
        view.tvDescribeGame.text = detailGame.describe
    }

}