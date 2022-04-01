package com.example.nbabettingapp.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class GamesMainResponseEntity (
    @SerializedName("data")
    val gamesResponseData: List<GamesResponseData> ?= null,

    @SerializedName("meta")
    val meta: Meta ?= null
)

@Entity(tableName = "game")
data class GamesResponseData(

    @PrimaryKey
    @SerializedName("id")
    val idGame: Int ?= null,

    @SerializedName("date")
    val date: String? = null,

    @SerializedName("home_team_score")
    val homeTeamScore: Int ?= null,

    @SerializedName("visitor_team_score")
    val awayTeamScore: Int ?= null,

    @SerializedName("season")
    val season: Int ?= null,

    @SerializedName("status")
    val status: String ?= null,

    @Embedded
    @SerializedName("home_team")
    val homeTeam: TeamsData ?= null,

    @Embedded
    @SerializedName("visitor_team")
    val visitorTeam: VisitorTeamData ?= null

): Serializable

