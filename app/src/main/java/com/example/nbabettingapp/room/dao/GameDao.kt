package com.example.nbabettingapp.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.nbabettingapp.entities.GamesResponseData

@Dao
interface GameDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun add(gamesResponseData: GamesResponseData)

    @Query("SELECT * FROM game")
    fun getAll():LiveData<List<GamesResponseData>>

    @Delete
    suspend fun delete(gamesResponseData: GamesResponseData)
}