package com.dalvik.pokemonkotlin.models

import com.google.gson.annotations.SerializedName

data class ResponseRegion(
    @SerializedName("pokemon_species")
    val pokemonList: List<Pokemon>
)
