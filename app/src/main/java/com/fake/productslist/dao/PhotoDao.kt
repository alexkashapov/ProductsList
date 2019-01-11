package com.fake.productslist.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import com.fake.productslist.entities.Photo

@Dao
interface PhotoDao {
    @Insert
    fun insert(photo: Photo):Long

}