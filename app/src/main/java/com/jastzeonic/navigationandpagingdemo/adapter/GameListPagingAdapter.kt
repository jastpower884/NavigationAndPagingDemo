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
import com.jastzeonic.navigationandpagingdemo.view.StateItemViewHolder

class GameListPagingAdapter(var clickListener: (view: View, url: String) -> Unit) : PagedListAdapter<GameInfoModel, RecyclerView.ViewHolder>(POST_COMPARATOR) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when (viewType) {
            R.layout.item_network_state -> StateItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_network_state, parent, false))
            R.layout.item_game_info_view -> GameInfoViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_game_info_view, parent, false), { view, position ->
                clickListener(view, getItem(position)?.detailUrl ?: "")
            })
            else -> throw IllegalArgumentException("unknown view type $viewType")
        }

    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is GameInfoViewHolder) {
            val viewHolder = holder as GameInfoViewHolder

            val item = getItem(position)

            viewHolder.titleText.text = item?.gameTitle
            viewHolder.firmText.text = item?.firm
            viewHolder.dataText.text = item?.release_date
            viewHolder.idText.text = item?.id.toString()
        }
    }


    private var state: Int = 1

    fun setState(state: Int) {
        val previousState = this.state
        val hadExtraRow = hasExtraRow()
        this.state = state
        val hasExtraRow = hasExtraRow()
        if (hadExtraRow != hasExtraRow) {
            if (hadExtraRow) {
                notifyItemRemoved(super.getItemCount())
            } else {
                notifyItemInserted(super.getItemCount())
            }
        } else if (hasExtraRow && previousState != state) {
            notifyItemChanged(itemCount - 1)
        }
    }

    private fun hasExtraRow() = state == 0

    override fun getItemViewType(position: Int): Int {
        return if (hasExtraRow() && position == itemCount - 1) {
            R.layout.item_network_state
        } else {
            R.layout.item_game_info_view
        }
    }


    override fun getItemCount(): Int {
        return super.getItemCount() + if (hasExtraRow()) 1 else 0
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