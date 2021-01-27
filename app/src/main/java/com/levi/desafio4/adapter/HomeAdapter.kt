package com.levi.desafio4.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.levi.desafio4.R
import com.levi.desafio4.entity.Game

class HomeAdapter(var listGame: ArrayList<Game>, val click: onClickListenerGame): RecyclerView.Adapter<HomeAdapter.ItemHolder>() {

    class ItemHolder(itemView: View, listGame: ArrayList<Game>): RecyclerView.ViewHolder(itemView) {
        var imageGame: ImageView = itemView.findViewById(R.id.ivGame)
        var nameGame: TextView = itemView.findViewById(R.id.tvNameGame)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val itemHolder = LayoutInflater.from(parent.context).inflate(R.layout.item_game, parent, false)
        return ItemHolder(itemHolder, listGame)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val game = listGame[position]

        holder.imageGame.setImageResource(R.drawable.splash_firebase)
        holder.nameGame.text = game.name

        holder.itemView.setOnClickListener {
            click.gameClick(position)
        }
    }

    override fun getItemCount() = listGame.size
}