package com.example.nbabettingapp.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class StatsMainResponseEntity(
    @SerializedName("data")
    val data: List<StatsData> ?= null,

    @SerializedName("meta")
    val meta: Meta ?= null
)

@Entity(tableName = "stats")
data class StatsData(
    @PrimaryKey
    @SerializedName("id")
    val statsId: Int ?= null,

    @Embedded
    @SerializedName("player")
    val player: Player ?= null,

    @SerializedName("ast")
    val ast: Int ?= null,

    @SerializedName("blk")
    val blk: Int ?= null,

    @SerializedName("dreb")
    val dreb: Int ?= null,

    @SerializedName("fc3_pct")
    val fc3_pct: Double ?= null,

    @SerializedName("fg3a")
    val fga3: Int ?= null,

    @SerializedName("fg3m")
    val fg3m: Int ?= null,

    @SerializedName("fg_pct")
    val fg_pct: String ?= null,

    @SerializedName("fga")
    val fga: Int ?= null,

    @SerializedName("fgm")
    val fgm: Int ?= null,

    @SerializedName("min")
    val min: String ?= null,

    @SerializedName("pts")
    val pts: String ?= null,

    @SerializedName("reb")
    val reb: String ?= null,

    @SerializedName("turnover")
    val turnover: String ?= null,

)

data class Player(
    @SerializedName("id")
    val idPlayer: Int ?= null,

    @SerializedName("first_name")
    val name: String ?= null,

    @SerializedName("height_feet")
    val feetHeight: String ?= null,

    @SerializedName("height_inches")
    val inchesHeight: String ?= null,

    @SerializedName("last_name")
    val lastName: String ?= null,

    @SerializedName("position")
    val position: String ?= null,

    @SerializedName("weight_pounds")
    val weightPounds: String ?= null,

    @SerializedName("team_id")
    val teamId: Int ?= null
)