package com.dalvik.pokemonkotlin.network

import com.dalvik.pokemonkotlin.models.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @GET("generation/{generation}")
    fun getGeneration(@Path("generation") generation: Int): Call<ResponseRegion>

    @GET("pokemon/{pokemon}")
    fun getPokemonDetail(@Path("pokemon") pokemon: Int): Call<ResponsePokemonDetail>

    /**
     * Llamada con sealed class
     * */
    @GET("generation/{generation}")
    suspend fun getGenerationSuspend(@Path("generation") generation: Int): Response<ResponseRegion>

    /**
     * Request method post with retrofit
     * */

    @POST("authentication/token/validate_with_login")
    fun createSession(@Body requestLogin:  RequestLogin): Call<ResponseLogin>

    @GET("authentication/token/new")
     fun generateToken(): Call<ResponseLogin>

    @FormUrlEncoded
    @POST("authentication/session/new")
    fun getSessionId(@Field("request_token") requestToken:  String): Call<ResponseLogin>
}