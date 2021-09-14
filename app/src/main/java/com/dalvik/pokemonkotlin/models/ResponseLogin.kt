package com.dalvik.pokemonkotlin.models

import com.google.gson.annotations.SerializedName

data class ResponseLogin(
    @SerializedName("success")
    val isSuccess: Boolean,

    @SerializedName("expires_at")
    val expire: String,

    @SerializedName("request_token")
    val requestToque: String,

    @SerializedName("session_id")
    val sessionId:String
)
