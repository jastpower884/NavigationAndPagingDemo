package com.jastzeonic.navigationandpagingdemo.database

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.persistence.room.Room
import android.content.Context
import android.os.AsyncTask
import android.util.Log
import com.google.gson.Gson
import com.jastzeonic.navigationandpagingdemo.model.Data
import com.jastzeonic.navigationandpagingdemo.model.GameInfoModel
import org.json.JSONObject

class GameInfoRepository : RepositoryProvider.DatabaseRepository {


    private lateinit var gameInfoDao: GameInfoDao
    private lateinit var database: Database

    override fun init(applicationContext: Context?) {

        if (applicationContext == null) {
            return
        }

        AsyncTask.execute({
            database = Room.databaseBuilder(applicationContext, Database::class.java, Database.DATABASE_NAME)
                    .build()
            gameInfoDao = database.getGameInfoDao()
            if (gameInfoDao.getAll().isEmpty()) {
                initData(applicationContext)
            }

        })


    }

    private fun initData(applicationContext: Context) {
        val inputStream = applicationContext.assets.open("data.json")
        val size = inputStream.available()
        var json = ""

        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        json = String(buffer)

        val obj = JSONObject(json)
        Log.d("#####", obj.toString())

        val jsonJavaRootObject = Gson().fromJson(json, Data::class.java)
        Log.d("#####", "" + jsonJavaRootObject.game_list.size)


        val result = ArrayList<GameInfoModel>()

        for (infoModel in jsonJavaRootObject.game_list) {

            val gameInfoModel = GameInfoModel()
            gameInfoModel.gameTitle = infoModel.game_title
            gameInfoModel.firm = infoModel.firm
            gameInfoModel.platform = infoModel.platform
            gameInfoModel.detailUrl = infoModel.detail
            gameInfoModel.imageUrl = infoModel.image_url
            gameInfoModel.release_date = infoModel.release_date

            result.add(gameInfoModel)

        }
        gameInfoDao.insert(result)
    }


    fun getAll(): LiveData<List<GameInfoModel>> {

        val liveData = MutableLiveData<List<GameInfoModel>>()

        DataAccessTask(gameInfoDao, {

            liveData.value = it

        }).execute()

        return liveData

    }


    fun getItemByPage(startIndex: Int, limit: Int): LiveData<List<GameInfoModel>> {

        val liveData = MutableLiveData<List<GameInfoModel>>()

        DataAccessTask2(gameInfoDao, startIndex, limit, {

            liveData.value = it

        }).execute()

        return liveData

    }


    private class DataAccessTask(var gameInfoDao: GameInfoDao, val callback: (result: List<GameInfoModel>) -> Unit) :
            AsyncTask<Void, Void, List<GameInfoModel>>() {
        override fun doInBackground(vararg params: Void?): List<GameInfoModel> {
            return gameInfoDao.getAll()
        }

        override fun onPostExecute(result: List<GameInfoModel>?) {
            super.onPostExecute(result)
            callback(result ?: emptyList())

        }

    }

    private class DataAccessTask2(val gameInfoDao: GameInfoDao, val startIndex: Int, val limit: Int, val callback: (result: List<GameInfoModel>) -> Unit) :
            AsyncTask<Void, Void, List<GameInfoModel>>() {
        override fun doInBackground(vararg params: Void?): List<GameInfoModel> {
            Thread.sleep(1000)
            return gameInfoDao.getDataByPage(startIndex.toLong(), limit)
        }

        override fun onPostExecute(result: List<GameInfoModel>?) {
            super.onPostExecute(result)
            callback(result ?: emptyList())

        }

    }


}