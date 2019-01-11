package com.fake.productslist.entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class Category(@PrimaryKey(autoGenerate = true)
               var id:Long,
               var name: String,
               var photoId:Long)