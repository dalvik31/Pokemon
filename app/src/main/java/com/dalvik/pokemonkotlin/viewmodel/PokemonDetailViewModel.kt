package com.dalvik.pokemonkotlin.viewmodel

import androidx.lifecycle.ViewModel
import com.dalvik.pokemonkotlin.models.ResponsePokemonDetail
import com.dalvik.pokemonkotlin.repository.PokemonDetailRepository

class PokemonDetailViewModel : ViewModel() {

    fun getPokemonDetail(
        pokemonNumber: Int,
        onResult: (response: ResponsePokemonDetail) -> Unit
    ) {


        PokemonDetailRepository.getInstance().getPokemonDetail(pokemonNumber){ isSuccess, response ->
            if(isSuccess && response!=null){
                onResult(response)
            }
        }
    }
}