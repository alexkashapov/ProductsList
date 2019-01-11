package com.fake.productslist.dao

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.fake.productslist.entities.Category
import com.fake.productslist.entities.Photo
import com.fake.productslist.entities.Record

@Database(entities = arrayOf(Record::class,Category::class, Photo::class),version = 1)
abstract class AppDatabase:RoomDatabase() {
    abstract fun recordDao():RecordDao
    abstract fun categoryDao():CategoryDao
    abstract fun photoDao():PhotoDao
}