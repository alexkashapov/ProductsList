package com.fake.productslist.model

data class Product(val name:String="Product",
                   val count:Double=0.0,
                   val type_count:String="шт",
                   val comment:String="",
                   val price: Double=0.0,
                   val category: String="Другое")
