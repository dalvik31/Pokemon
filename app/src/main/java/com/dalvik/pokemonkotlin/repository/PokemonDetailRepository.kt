package com.dalvik.pokemonkotlin.repository

import android.util.Log
import com.dalvik.pokemonkotlin.models.ResponsePokemonDetail
import com.dalvik.pokemonkotlin.models.ResponsePokemonDetailV2
import com.dalvik.pokemonkotlin.network.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokemonDetailRepository {

    companion object {
        private var INSTANCE: PokemonDetailRepository? = null

        fun getInstance() = INSTANCE
            ?: PokemonDetailRepository().also {
                INSTANCE = it
            }
    }


    fun getPokemonDetail(
        numberPokemon: Int,
        onResult: (isSuccess: Boolean, response: ResponsePokemonDetailV2?) -> Unit
    ) {
        RetrofitBuilder.apiServiceDeatailV2.getPokemonDetailV2(numberPokemon).enqueue(object :
            Callback<List<ResponsePokemonDetailV2>> {
            override fun onResponse(
                call: Call<List<ResponsePokemonDetailV2>>,
                response: Response<List<ResponsePokemonDetailV2>>
            ) {
                if( response.body()!=null){
                    onResult(response.isSuccessful, response.body()!![0])
                }else{
                    onResult(false, null)
                }

            }

            override fun onFailure(call: Call<List<ResponsePokemonDetailV2>>, t: Throwable) {
                Log.e("error","error: ${t.message}")
                onResult(false, null)
            }

        })
    }
}