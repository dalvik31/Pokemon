package com.dalvik.pokemonkotlin.models

import com.google.gson.annotations.SerializedName

data class ResponsePokemonDetailV2(

    @SerializedName("number")
    val id: Int,

    @SerializedName("description")
    val description: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("species")
    val specie: String,

    @SerializedName("height")
    val height: String,

    @SerializedName("weight")
    val weight: String,

    @SerializedName("types")
    val type: List<String>,

    ) {

    fun formatNumber(): String {
        return id.toString()
    }

    fun getFirstType(): String {
        return type[0]
    }

    fun getSecondType(): String {
        return if (hasSecondType()) type[1] else ""
    }

    fun hasSecondType(): Boolean {
        return type.size > 1
    }
}
