package com.jastzeonic.navigationandpagingdemo.adapter

import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.jastzeonic.navigationandpagingdemo.R
import com.jastzeonic.navigationandpagingdemo.model.GameInfoModel
import com.jastzeonic.navigationandpagingdemo.view.GameInfoViewHolder

class GameListPagingAdapter(var clickListener: (view: View, url: String) -> Unit) : PagedListAdapter<GameInfoModel, RecyclerView.ViewHolder>(POST_COMPARATOR) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return GameInfoViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_game_info_view, parent, false), { view, position ->

            clickListener(view, getItem(position)?.detailUrl ?: "")


        })
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewHolder = holder as GameInfoViewHolder

        val item = getItem(position)

        viewHolder.titleText.text = item?.gameTitle
        viewHolder.firmText.text = item?.firm
        viewHolder.dataText.text = item?.release_date
        viewHolder.idText.text = item?.id.toString()
    }


    companion object {
        private val PAYLOAD_SCORE = Any()
        val POST_COMPARATOR = object : DiffUtil.ItemCallback<GameInfoModel>() {
            override fun areContentsTheSame(oldItem: GameInfoModel, newItem: GameInfoModel): Boolean =
                    oldItem == newItem

            override fun areItemsTheSame(oldItem: GameInfoModel, newItem: GameInfoModel): Boolean =
                    oldItem.id == newItem.id

            override fun getChangePayload(oldItem: GameInfoModel, newItem: GameInfoModel): Any? {
                return if (sameExceptScore(oldItem, newItem)) {
                    PAYLOAD_SCORE
                } else {
                    null
                }
            }
        }

        private fun sameExceptScore(oldItem: GameInfoModel, newItem: GameInfoModel): Boolean {
            // DON'T do this copy in a real app, it is just convenient here for the demo :)
            // because reddit randomizes scores, we want to pass it as a payload to minimize
            // UI updates between refreshes
            return false
        }
    }

}