package com.jastzeonic.navigationandpagingdemo.adapter

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.jastzeonic.navigationandpagingdemo.R
import com.jastzeonic.navigationandpagingdemo.model.GameInfoModel
import com.jastzeonic.navigationandpagingdemo.view.GameInfoViewHolder

class GameListNormalAdatper(var items: List<GameInfoModel>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return GameInfoViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_game_info_view, parent, false), { view, url -> })
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewHolder = holder as GameInfoViewHolder
        viewHolder.titleText.text = items[position].gameTitle
        viewHolder.firmText.text = items[position].firm
        viewHolder.dataText.text = items[position].release_date
        viewHolder.idText.text = items[position].id.toString()
    }

}