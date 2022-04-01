package com.example.nbabettingapp.entities

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class TeamsMainResponseEntity (
    @SerializedName("data")
    val data: List<TeamsData> ?= null,

    @SerializedName("meta")
    val meta: Meta ?= null
)

data class TeamsData(
    @SerializedName("id")
    val teamID: Int ?= null,

    @SerializedName("abbreviation")
    val shortName: String ?= null,

    @SerializedName("city")
    val city: String ?= null,

    @SerializedName("conference")
    val conf: String ?= null,

    @SerializedName("division")
    val division: String ?= null,

    @SerializedName("full_name")
    val fullName: String ?= null,

    @SerializedName("name")
    val name: String ?= null,
): Serializable

data class VisitorTeamData(
    @SerializedName("id")
    val visitorID: Int ?= null,

    @SerializedName("abbreviation")
    val shortNameVisitor: String ?= null,

    @SerializedName("city")
    val cityVisitor: String ?= null,

    @SerializedName("conference")
    val confVisitor: String ?= null,

    @SerializedName("division")
    val divisionVisitor: String ?= null,

    @SerializedName("full_name")
    val fullNameVisitor: String ?= null,

    @SerializedName("name")
    val nameVisitor: String ?= null,
): Serializable

data class Meta(
    @SerializedName("total_pages")
    val pages: Int ?= null,

    @SerializedName("current_page")
    val currentPage: Int ?= null
)