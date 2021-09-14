package com.dalvik.pokemonkotlin.models

import com.google.gson.annotations.SerializedName

data class Name(
    @SerializedName("english")
    val name:String
)
