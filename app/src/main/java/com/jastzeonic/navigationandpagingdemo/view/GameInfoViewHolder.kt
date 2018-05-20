package com.jastzeonic.navigationandpagingdemo.view

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.jastzeonic.navigationandpagingdemo.R

class GameInfoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var titleText: TextView
    var firmText: TextView

    init {

        titleText = itemView.findViewById(R.id.title_text)
        firmText = itemView.findViewById(R.id.firm_text)

    }

}