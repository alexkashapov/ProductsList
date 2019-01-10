package com.fake.productslist.model

import java.util.*

data class BuyList(val name:String,
                   val date: Date,
                   val products:List<Product>)