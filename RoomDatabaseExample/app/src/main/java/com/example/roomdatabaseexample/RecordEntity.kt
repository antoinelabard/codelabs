package com.example.roomdatabaseexample

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recorddatabase")
data class RecordEntity(
    @PrimaryKey(autoGenerate = true) val rId: Int,
    @ColumnInfo(name = "creation_date") val creationDate: String?,
    @ColumnInfo(name = "locationList") val locationList: Int?
)
