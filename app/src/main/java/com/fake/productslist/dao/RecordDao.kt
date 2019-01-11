package com.fake.productslist.dao

import android.arch.persistence.room.*
import com.fake.productslist.entities.Record

@Dao
interface RecordDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdate(record: Record): Long

    @Delete
    fun delete(record: Record)

    @Query("SELECT * FROM record")
    fun getAll(): List<Record>

}