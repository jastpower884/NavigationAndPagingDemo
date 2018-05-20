package com.jastzeonic.navigationandpagingdemo.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey


@Entity(tableName = GameInfoModel.TABLE_NAME)
class GameInfoModel {

    companion object {
        const val TABLE_NAME = "game_info"

    }


    @PrimaryKey(autoGenerate = true)
    var id:Long? = null


    var gameTitle = ""
    var firm = ""
    var platform = ""
    var detailUrl = ""
    var imageUrl = ""
    var release_date = ""


}