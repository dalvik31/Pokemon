package com.dalvik.pokemonkotlin.repository

import com.dalvik.pokemonkotlin.models.ResponseRegion
import com.dalvik.pokemonkotlin.network.PokemonResult
import com.dalvik.pokemonkotlin.network.RetrofitBuilder
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.HttpURLConnection
import java.net.URL

class PokemonRepository {
    companion object {
        private var INSTANCE: PokemonRepository? = null
        fun getInstance() = INSTANCE
            ?: PokemonRepository().also {
                INSTANCE = it
            }
    }


    // GET repo list
    fun getGenerationRetrofit(
        region: Int,
        onResult: (isSuccess: Boolean, response: ResponseRegion?, error: String) -> Unit
    ) {

        RetrofitBuilder.apiService.getGeneration(region).enqueue(object : Callback<ResponseRegion> {
            override fun onResponse(
                call: Call<ResponseRegion>?,
                response: Response<ResponseRegion>?
            ) {
                if (response != null && response.isSuccessful)
                    onResult(true, response.body(), "")
                else
                    onResult(false, null,"No se puede leer la respuesta")
            }

            override fun onFailure(call: Call<ResponseRegion>?, t: Throwable?) {
                onResult(false, null,"Error al descargar la informacion")
            }

        })
    }


    /**
     * Se hace la llamada al api por metodo GET, con HttpURLConnection y con sealed class
     *
     * */
    fun getGenerationHttp(region: Int): PokemonResult {
        val baseUrl = "https://pokeapi.co/api/v2/generation/$region"
        val url = URL(baseUrl)
        (url.openConnection() as? HttpURLConnection)?.run {
            if (responseCode == HttpURLConnection.HTTP_OK) {
                val response = inputStream.bufferedReader()
                    .use { it.readText() }
                return PokemonResult.Success(Gson().fromJson(response, ResponseRegion::class.java))

            } else
                return PokemonResult.Failure(Exception("Cannot open HttpURLConnection"))
        }
        return PokemonResult.Failure(Exception("Cannot open HttpURLConnection"))
    }


     suspend fun getRegionsWithSealedRetrofit(region: Int): PokemonResult = withContext(Dispatchers.IO) {

        try {
            val response = RetrofitBuilder.apiService.getGenerationSuspend(region)
            if (response.isSuccessful && response.body()!=null && response.body()!!.pokemonList.isNotEmpty()) {
                return@withContext PokemonResult.Success(response.body()!!)
            } else {
                return@withContext PokemonResult.Failure(Exception("Cannot open HttpURLConnection"))
            }
        } catch (e: Exception) {
            return@withContext PokemonResult.Failure(e)
        }
    }

}