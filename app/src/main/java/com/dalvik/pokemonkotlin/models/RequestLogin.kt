package com.dalvik.pokemonkotlin.models

import com.google.gson.annotations.SerializedName

data class RequestLogin(
    @SerializedName("username")
    val userName: String,

    @SerializedName("password")
    val password: String,

    @SerializedName("request_token")
    val requestToque: String
)
