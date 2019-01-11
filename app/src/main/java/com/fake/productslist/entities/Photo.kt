package com.fake.productslist.entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class Photo(
        @PrimaryKey(autoGenerate = true)
        var photoId:Long,
        var path: String)