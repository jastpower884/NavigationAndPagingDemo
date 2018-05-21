package com.jastzeonic.navigationandpagingdemo.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.jastzeonic.navigationandpagingdemo.model.GameInfoModel

@Database(entities = [(GameInfoModel::class)], version = 1, exportSchema = false )
abstract class Database : RoomDatabase() {

    companion object {
        const val DATABASE_NAME = "database"
    }


    abstract fun getGameInfoDao(): GameInfoDao

}