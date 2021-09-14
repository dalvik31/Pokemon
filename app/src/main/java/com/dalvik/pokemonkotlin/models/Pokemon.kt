package com.dalvik.pokemonkotlin.models

import com.dalvik.pokemonkotlin.utils.Constants
import com.google.gson.annotations.SerializedName

data class Pokemon(
    @SerializedName("name")
    val name: String,

    @SerializedName("url")
    val url: String,

    ) {

    @JvmName("urlPokemon")
    fun getUrl() :String{
        return Constants.URL_IMAGE_THUMBNAIL_POKEMON.plus(name).plus(".png")
    }
    fun getNumber(): Int {
        val stringSplit = url.split("/")
        return stringSplit[stringSplit.size - 2].toInt()
    }

    override fun toString(): String {
        return name
    }
}
