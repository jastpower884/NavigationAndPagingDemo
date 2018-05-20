package com.jastzeonic.navigationandpagingdemo.database

import android.app.Application
import android.content.Context
import android.support.annotation.WorkerThread


class RepositoryProvider {

    interface Repository

    interface DatabaseRepository {
        fun init(applicationContext: Context?)
    }

    companion object {

        private const val DEFAULT_KEY = "RepositoryProvider.DefaultKey"
        private val repositoryStore: HashMap<String, DatabaseRepository> = HashMap()

        fun <T : DatabaseRepository> initDatabase(modelClass: Class<T>, applicationContext: Application) {
            var result = repositoryStore[DEFAULT_KEY + ":" + modelClass.canonicalName]
            if (result == null) {
                result = modelClass.newInstance()
                result.init(applicationContext)
                repositoryStore[DEFAULT_KEY + ":" + modelClass.canonicalName] = result
            } else {
                throw IllegalStateException("Database already init. why are you do that again?") as Throwable
            }


        }

        @Suppress("UNCHECKED_CAST")
        fun <T : DatabaseRepository> getDatabaseRepository(modelClass: Class<T>): T {

            val result: DatabaseRepository? = repositoryStore[DEFAULT_KEY + ":" + modelClass.canonicalName]
                    ?: throw IllegalStateException("for use to access database. you got to init database first.")

            return result as T

        }


    }


}