package com.example.nbabettingapp.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.nbabettingapp.entities.GamesResponseData
import com.example.nbabettingapp.entities.StatsData
import com.example.nbabettingapp.room.dao.GameDao
import com.example.nbabettingapp.room.dao.StatsDao
import kotlinx.coroutines.InternalCoroutinesApi

@Database(entities = [GamesResponseData::class, StatsData::class], version = 2, exportSchema = false)
abstract class GameDatabase : RoomDatabase(){

    abstract fun gameDao(): GameDao
    abstract fun statsDao(): StatsDao

    companion object{
        @Volatile
        private var INSTANCE: GameDatabase?= null

        @InternalCoroutinesApi
        fun getDatabase(context: Context): GameDatabase {
            val instance = INSTANCE
            if(instance != null){
                return instance
            }
            synchronized(this){
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    GameDatabase::class.java,
                    "nba_db").build()
                return INSTANCE as GameDatabase
            }
        }
    }
}