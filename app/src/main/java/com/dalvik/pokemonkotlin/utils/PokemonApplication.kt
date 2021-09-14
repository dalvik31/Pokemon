package com.dalvik.pokemonkotlin.utils

import android.app.Application

class PokemonApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object{
        lateinit var instance: PokemonApplication
    }
}