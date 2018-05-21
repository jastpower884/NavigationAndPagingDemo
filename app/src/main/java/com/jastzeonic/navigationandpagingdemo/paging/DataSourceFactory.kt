package com.jastzeonic.navigationandpagingdemo.paging

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource
import com.jastzeonic.navigationandpagingdemo.database.GameInfoRepository
import com.jastzeonic.navigationandpagingdemo.model.GameInfoModel


class DataSourceFactory(private val lifecycleOwner: LifecycleOwner, private val repository: GameInfoRepository,
                        private val statusCallback: MutableLiveData<Int>
) : DataSource.Factory<Int, GameInfoModel>() {
    override fun create(): DataSource<Int, GameInfoModel> {
        return PagingDataSource(lifecycleOwner, repository, statusCallback)
    }

}