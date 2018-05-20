package com.jastzeonic.navigationandpagingdemo

import android.app.Application
import com.jastzeonic.navigationandpagingdemo.database.GameInfoRepository
import com.jastzeonic.navigationandpagingdemo.database.RepositoryProvider


class DemoApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        RepositoryProvider.initDatabase(GameInfoRepository::class.java, this)
    }

}