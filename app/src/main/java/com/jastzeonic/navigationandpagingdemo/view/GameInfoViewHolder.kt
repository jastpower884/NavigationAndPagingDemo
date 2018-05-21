package com.jastzeonic.navigationandpagingdemo.view

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.jastzeonic.navigationandpagingdemo.R

class GameInfoViewHolder(itemView: View, clickListener: (view: View, position: Int) -> Unit) : RecyclerView.ViewHolder(itemView) {

    var titleText: TextView = itemView.findViewById(R.id.title_text)
    var firmText: TextView = itemView.findViewById(R.id.firm_text)
    var dataText: TextView = itemView.findViewById(R.id.date_text)
    var thumbnailImage: ImageView = itemView.findViewById(R.id.thumbnail_image)
    var idText: TextView = itemView.findViewById(R.id.id_text)


    init {
        itemView.setOnClickListener {


            clickListener(it, adapterPosition)
        }
    }

}