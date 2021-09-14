package com.dalvik.pokemonkotlin.network

import com.dalvik.pokemonkotlin.utils.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {

     private fun getRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun getRetrofitDetail(): Retrofit{
        return  Retrofit.Builder()
            .baseUrl(Constants.URL_POKEMON_DETAIL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(HeaderInterceptor()).build()


    private fun getRetrofitLogin(): Retrofit{

        return  Retrofit.Builder()
            .baseUrl(Constants.BASE_URL_LOGIN)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }


    val apiService: ApiService = getRetrofit().create(ApiService::class.java)
    val apiServiceDeatail:ApiService =  getRetrofitDetail().create(ApiService::class.java)
    val apiServiceLogin:ApiService =  getRetrofitLogin().create(ApiService::class.java)


}