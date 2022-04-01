package com.example.nbabettingapp.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.nbabettingapp.entities.StatsData

@Dao
interface StatsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun add(statsData: StatsData)

    @Query("SELECT * FROM stats")
    fun getAll(): LiveData<List<StatsData>>

    @Delete
    suspend fun delete(statsData: StatsData)
}