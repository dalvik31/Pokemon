package com.dalvik.pokemonkotlin.network

import android.os.Build
import com.dalvik.pokemonkotlin.BuildConfig
import com.dalvik.pokemonkotlin.utils.Constants
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val url = chain
            .request()
            .url
            .newBuilder()
            .addQueryParameter(Constants.PARAMETER_API_KEY,BuildConfig.API_KEY_TMBD)
            .build()
       return  chain.proceed(chain.request().newBuilder().url(url).build())
    }
}