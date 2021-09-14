package com.dalvik.pokemonkotlin.repository

import android.util.Log
import com.dalvik.pokemonkotlin.models.ResponsePokemonDetail
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
        onResult: (isSuccess: Boolean, response: ResponsePokemonDetail?) -> Unit
    ) {
        RetrofitBuilder.apiServiceDeatail.getPokemonDetail(numberPokemon).enqueue(object :
            Callback<ResponsePokemonDetail> {
            override fun onResponse(
                call: Call<ResponsePokemonDetail>,
                response: Response<ResponsePokemonDetail>
            ) {
                onResult(response.isSuccessful, response.body())
            }

            override fun onFailure(call: Call<ResponsePokemonDetail>, t: Throwable) {
                onResult(false, null)
            }

        })
    }
}