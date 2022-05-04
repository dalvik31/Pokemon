package com.dalvik.pokemonkotlin.viewmodel

import androidx.lifecycle.ViewModel
import com.dalvik.pokemonkotlin.models.ResponsePokemonDetail
import com.dalvik.pokemonkotlin.models.ResponsePokemonDetailV2
import com.dalvik.pokemonkotlin.repository.PokemonDetailRepository

class PokemonDetailViewModel : ViewModel() {

    fun getPokemonDetail(
        pokemonNumber: Int,
        onResult: (response: ResponsePokemonDetailV2?) -> Unit
    ) {


        PokemonDetailRepository.getInstance().getPokemonDetail(pokemonNumber){ isSuccess, response ->
            if(isSuccess && response!=null){
                onResult(response)
            }else{
                onResult(null)
            }
        }
    }
}