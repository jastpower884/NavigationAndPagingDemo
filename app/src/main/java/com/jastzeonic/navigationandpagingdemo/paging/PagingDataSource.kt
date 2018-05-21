package com.jastzeonic.navigationandpagingdemo.paging

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.arch.paging.PageKeyedDataSource
import com.jastzeonic.navigationandpagingdemo.database.GameInfoRepository
import com.jastzeonic.navigationandpagingdemo.model.GameInfoModel

class PagingDataSource(private val lifecycleOwner: LifecycleOwner,
                       private val repository: GameInfoRepository,
                       private val statusCallback: MutableLiveData<Int>
) : PageKeyedDataSource<Int, GameInfoModel>() {


    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, GameInfoModel>) {

        statusCallback.postValue(0)

        repository.getItemByPage(0, params.requestedLoadSize).observe(lifecycleOwner,
                Observer {
                    statusCallback.postValue(1)
                    callback.onResult(it ?: emptyList(), 0, (0 + params.requestedLoadSize))

                })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, GameInfoModel>) {

        statusCallback.postValue(0)
        repository.getItemByPage(params.key.toInt(), params.requestedLoadSize).observe(lifecycleOwner,
                Observer {
                    statusCallback.postValue(1)
                    callback.onResult(it
                            ?: emptyList(), (params.key + params.requestedLoadSize))
                })


    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, GameInfoModel>) {
    }

}