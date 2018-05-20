package com.jastzeonic.navigationandpagingdemo

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.google.gson.Gson
import com.jastzeonic.navigationandpagingdemo.database.GameInfoRepository
import com.jastzeonic.navigationandpagingdemo.database.RepositoryProvider
import com.jastzeonic.navigationandpagingdemo.model.Data
import com.jastzeonic.navigationandpagingdemo.model.GameInfoModel
import com.jastzeonic.navigationandpagingdemo.model.InfoModel
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
