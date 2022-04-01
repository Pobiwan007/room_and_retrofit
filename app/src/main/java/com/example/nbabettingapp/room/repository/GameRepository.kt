package com.example.nbabettingapp.room.repository

import com.example.nbabettingapp.entities.GamesResponseData
import com.example.nbabettingapp.room.dao.GameDao


class GameRepository(private val dao: GameDao) {
    val all = dao.getAll()

    suspend fun add(gamesResponseData: GamesResponseData){
        dao.add(gamesResponseData)
    }

    suspend fun delete(gamesResponseData: GamesResponseData){
        dao.delete(gamesResponseData)
    }
}