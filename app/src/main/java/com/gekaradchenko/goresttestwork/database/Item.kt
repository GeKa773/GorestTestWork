package com.gekaradchenko.goresttestwork.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "item_table")
data class Item(
    @PrimaryKey
    var itemId: Int,
    var title: String,
    var body: String,
)