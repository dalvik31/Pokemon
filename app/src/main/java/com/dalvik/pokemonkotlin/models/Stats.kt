package com.dalvik.pokemonkotlin.models

import com.google.gson.annotations.SerializedName

data class Stats(
    @SerializedName("HP")
    val hp: Int,

    @SerializedName("Attack")
    val attack: Int,

    @SerializedName("Defense")
    val defence: Int,

    @SerializedName("Sp. Attack")
    val especialAttac: Int,

    @SerializedName("Sp. Defense")
    val especialDefence: Int,

    @SerializedName("Speed")
    val speed: Int
)
