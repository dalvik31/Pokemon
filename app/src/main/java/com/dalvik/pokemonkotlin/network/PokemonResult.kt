package com.dalvik.pokemonkotlin.network

import com.dalvik.pokemonkotlin.models.ResponseRegion
import java.lang.Exception

sealed class PokemonResult{
    data class Success(val data: ResponseRegion): PokemonResult()
    data class Failure(val exception: Exception) : PokemonResult()
    class Loading : PokemonResult()
}
