package com.dalvik.pokemonkotlin.network

import com.dalvik.pokemonkotlin.models.ResponseLogin

sealed class LoginResult{
    data class Success(val responseLogin: ResponseLogin): LoginResult()
    data class Failure(val errorResponse: Exception): LoginResult()
}
