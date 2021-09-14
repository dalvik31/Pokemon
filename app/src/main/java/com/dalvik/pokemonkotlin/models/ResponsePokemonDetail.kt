package com.dalvik.pokemonkotlin.models

import com.google.gson.annotations.SerializedName

data class ResponsePokemonDetail(

    @SerializedName("id")
    val id: Int,

    @SerializedName("description")
    val description: String,

    @SerializedName("name")
    val name: Name,

    @SerializedName("type")
    val type: List<String>,

    @SerializedName("base")
    val stats: Stats,

    @SerializedName("profile")
    val profile: Profile
) {

    fun formatNumber():String{
        return id.toString()
    }
    fun getFirstType(): String {
        return type[0]
    }

    fun getSecondType(): String {
        return if (type.size > 1) type[1] else ""
    }

    fun hasSecondType():Boolean{
        return type.size > 1
    }
}
