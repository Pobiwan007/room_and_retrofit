package com.example.nbabettingapp.room.repository

import com.example.nbabettingapp.entities.StatsData
import com.example.nbabettingapp.room.dao.StatsDao

class StatsRepository (private val dao: StatsDao) {
    val all = dao.getAll()

    suspend fun add(statsData: StatsData){
        dao.add(statsData)
    }

    suspend fun delete(statsData: StatsData){
        dao.delete(statsData)
    }
}