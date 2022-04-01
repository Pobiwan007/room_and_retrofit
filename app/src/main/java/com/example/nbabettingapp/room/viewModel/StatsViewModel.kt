package com.example.nbabettingapp.room.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.nbabettingapp.entities.StatsData
import com.example.nbabettingapp.room.GameDatabase
import com.example.nbabettingapp.room.repository.StatsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch

@OptIn(InternalCoroutinesApi::class)
class StatsViewModel(app: Application): AndroidViewModel(app)  {
    val all: LiveData<List<StatsData>>
    private val repository: StatsRepository

    init {
        val dao = GameDatabase.getDatabase(app).statsDao()
        repository = StatsRepository(dao)
        all = repository.all
    }

    fun add(statsData: StatsData){
        viewModelScope.launch(Dispatchers.IO) {
            repository.add(statsData)
        }
    }

    fun delete(statsData: StatsData){
        viewModelScope.launch(Dispatchers.IO){
            repository.delete(statsData)
        }
    }
}