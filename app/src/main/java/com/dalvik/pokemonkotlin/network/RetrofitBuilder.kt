package com.dalvik.pokemonkotlin.network

import com.dalvik.pokemonkotlin.utils.Constants
import okhttp3.CacheControl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
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

    private fun getRetrofitDetailV2(): Retrofit{
       /* val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val httpClient = OkHttpClient.Builder()
// add your other interceptors …
        return  Retrofit.Builder()
            .baseUrl(Constants.URL_POKEMON_DETAIL_V2)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build()*/


        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC)
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
        return Retrofit.Builder()
            .baseUrl(Constants.URL_POKEMON_DETAIL_V2)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
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
    val apiServiceDeatailV2:ApiService =  getRetrofitDetailV2().create(ApiService::class.java)
    val apiServiceLogin:ApiService =  getRetrofitLogin().create(ApiService::class.java)


}