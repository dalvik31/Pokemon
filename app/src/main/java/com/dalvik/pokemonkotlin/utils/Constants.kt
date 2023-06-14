package com.dalvik.pokemonkotlin.utils

import com.dalvik.pokemonkotlin.R

class Constants {
    companion object {
        //Service
        const val BASE_URL = "https://pokeapi.co/api/v2/";
        @Deprecated("Page down")
        const val URL_POKEMON_DETAIL = "https://app.pokemon-api.xyz/"
        const val URL_POKEMON_DETAIL_V2 = "https://pokeapi.glitch.me/v1/"
        val IMAGES_URL = arrayOf("https://pokemon-project.com/pokedex/img/sprite/Home/","https://pokemon-project.com/pokedex/img/sprite/Home/shiny/")
        const val URL_IMAGE_THUMBNAIL_POKEMON = "https://img.pokemondb.net/sprites/home/normal/"
        const val BASE_URL_LOGIN = "https://api.themoviedb.org/3/"
        const val PARAMETER_API_KEY = "api_key"

        //Intents key
        const val PARAMETER_REGION_NAME = "REGION"
        const val PARAMETER_NUMBER_POKEMON = "NUMBER_POKEMON"

        // Pokemon types
        const val TYPE_BUG = "Bug"
        const val TYPE_DARK = "Dark"
        const val TYPE_DRAGON = "Dragon"
        const val TYPE_ELECTRIC = "Electric"
        const val TYPE_FAIRY = "Fairy"
        const val TYPE_FIGHTING = "Fighting"
        const val TYPE_FIRE = "Fire"
        const val TYPE_FLYING = "Flying"
        const val TYPE_GHOST = "Ghost"
        const val TYPE_GRASS = "Grass"
        const val TYPE_GROUND = "Ground"
        const val TYPE_ICE = "Ice"
        const val TYPE_NORMAL = "Normal"
        const val TYPE_POISON = "Poison"
        const val TYPE_PISCHIC = "Psychic"
        const val TYPE_ROCK = "Rock"
        const val TYPE_STEEL = "Steel"
        const val TYPE_WATER = "Water"

        //Pokemon region
        val NAME_REGIONS_ARRAY = arrayOf(
            R.string.region_kanto,
            R.string.region_johto,
            R.string.region_hoenn,
            R.string.region_sinnoh,
            R.string.region_teselia,
            R.string.region_kalos,
            R.string.region_alola,
            R.string.region_galar
        )
        val TOTAL_REGIONS = NAME_REGIONS_ARRAY.size


    }



}