package com.jastzeonic.navigationandpagingdemo.database

import android.arch.persistence.room.*
import com.jastzeonic.navigationandpagingdemo.model.GameInfoModel

@Dao
interface GameInfoDao {

    @Query("select * from " + GameInfoModel.TABLE_NAME)
    fun getAll(): List<GameInfoModel>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: GameInfoModel): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(items:List<GameInfoModel>)

    @Update
    fun update(item: GameInfoModel): Int


    @Delete
    fun delete(item: GameInfoModel): Int

}