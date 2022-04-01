package com.example.nbabettingapp.room.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.nbabettingapp.entities.GamesResponseData
import com.example.nbabettingapp.room.GameDatabase
import com.example.nbabettingapp.room.repository.GameRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch

@OptIn(InternalCoroutinesApi::class)
class GameViewModel(app: Application): AndroidViewModel(app)  {
    val all: LiveData<List<GamesResponseData>>
    private val repository: GameRepository

    init {
        val dao = GameDatabase.getDatabase(app).gameDao()
        repository = GameRepository(dao)
        all = repository.all
    }

    fun add(gamesResponseData: GamesResponseData){
        viewModelScope.launch(Dispatchers.IO) {
            repository.add(gamesResponseData)
        }
    }

    fun delete(gamesResponseData: GamesResponseData){
        viewModelScope.launch(Dispatchers.IO){
            repository.delete(gamesResponseData)
        }
    }
}