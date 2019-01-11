package com.fake.productslist.dao

import android.app.Application
import android.arch.persistence.room.Room


class App : Application() {

    var database: AppDatabase? = null
        private set

    override fun onCreate() {
        super.onCreate()
        instance = this
        database = Room.databaseBuilder(this, AppDatabase::class.java, "database")
                .build()
    }

    companion object {
        lateinit var instance: App
    }
}