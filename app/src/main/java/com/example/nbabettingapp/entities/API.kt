package com.example.nbabettingapp.entities

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.converter.gson.GsonConverterFactory

class ManagerAPI{
    fun getApi(): API{
        val request = Retrofit.Builder()
            .baseUrl("https://www.balldontlie.io")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return request.create(API::class.java)
    }
}

interface API {
    @GET("/api/v1/teams/")
    fun getTeams(): Call<TeamsMainResponseEntity>

    @GET("/api/v1/teams/")
    fun getTeamsPage(@Query("page") page: Int): Call<TeamsMainResponseEntity>

    @GET("/api/v1/games/")
    fun getGames(
        @Query("team_ids[]") idTeams: Int,
        @Query("seasons[]") season: Int
    ): Call<GamesMainResponseEntity>

    @GET("/api/v1/stats")
    fun getStatsGame(
        @Query("game_ids[]") id: Int
    ): Call<StatsMainResponseEntity>
}