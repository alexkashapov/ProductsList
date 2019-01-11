package com.fake.productslist.entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*

@Entity
data class Record(@PrimaryKey(autoGenerate = true)
             var id: Long,
             var startTime:Long,
             var endTime:Long,
             var duration:Long,
             var sum: String,
             var categoryId: Long)