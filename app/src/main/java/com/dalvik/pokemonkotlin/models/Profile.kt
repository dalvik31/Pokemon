package com.dalvik.pokemonkotlin.models

import com.google.gson.annotations.SerializedName

data class Profile(
    @SerializedName("height")
    val height: String,

    @SerializedName("weight")
    val weight: String,

    @SerializedName("gender")
    val gender: String
)
