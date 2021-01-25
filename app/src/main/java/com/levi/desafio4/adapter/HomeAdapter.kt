package com.levi.desafio4.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.levi.desafio4.R

class HomeAdapter(var listGame: ArrayList<String>, val click: onClickListenerGame): RecyclerView.Adapter<HomeAdapter.ItemHolder>() {

    class ItemHolder(itemView: View, listGame: ArrayList<String>): RecyclerView.ViewHolder(itemView) {
        var imageGame = itemView.findViewById<ImageView>(R.id.ivGame)
        var nameGame = itemView.findViewById<TextView>(R.id.tvNameGame)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val itemHolder = LayoutInflater.from(parent.context).inflate(R.layout.item_game, parent, false)
        return ItemHolder(itemHolder, listGame)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {

        val game = listGame[position]

        holder.itemView.setOnClickListener {
            click.gameClick(position)
        }
    }

    override fun getItemCount() = listGame.size
}