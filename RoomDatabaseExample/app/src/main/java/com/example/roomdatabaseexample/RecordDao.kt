package com.example.roomdatabaseexample

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RecordDao {
    @Query("SELECT * FROM recorddatabase")
    fun getAll(): List<RecordEntity>

    @Query("SELECT * FROM recorddatabase WHERE rId = (:rId)")
    fun getById(rId: Int): RecordEntity

    @Insert
    fun insert(recordEntity: RecordEntity)

    @Delete
    fun delete(recordEntity: RecordEntity)
}
