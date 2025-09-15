package com.dalvik.pokemonkotlin.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.dalvik.pokemonkotlin.models.Pokemon
import com.dalvik.pokemonkotlin.models.ResponseRegion
import com.dalvik.pokemonkotlin.network.PokemonResult
import com.dalvik.pokemonkotlin.repository.PokemonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class RegionViewModel : ViewModel() {
    val pokemonList = MutableLiveData<List<Pokemon>>()
    val showLoading= MutableLiveData(true)
    val msgError= MutableLiveData("")

    /**
     * Funcion para obetener el response de retrofit con un callback desde el repository
     * */
    fun getGeneration(region: Int, onResult: ( response: ResponseRegion?) -> Unit) {
        PokemonRepository.getInstance().getGenerationRetrofit(region) { isSuccess, response, error ->
            showLoading.postValue(false)
            if (isSuccess && response!=null) {
                msgError.postValue(error)
                onResult(response)
            }else{
                msgError.postValue(error)
            }
        }
    }


    /**
     *Funcion para obetener el response de HttpURLConnection con sealed class desde el repository
     * */
    fun getGenerationSealedHttp(region: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            when(val response = PokemonRepository.getInstance().getGenerationHttp(region)){
                is PokemonResult.Success ->  {
                    showLoading.postValue(false)
                    msgError.postValue("")
                    pokemonList.postValue( response.data.pokemonList)
                }
                is PokemonResult.Failure ->{
                    showLoading.postValue(false)
                    msgError.postValue(response.exception.message)
                }

                is PokemonResult.Loading -> {}
            }

        }
    }


    /**
     *Funcion para obetener el response de retrofit con sealed class desde el repository
     * */
    fun getGenerationSealedRetrofit(region : Int){

        viewModelScope.launch {
            when (val result = PokemonRepository.getInstance().getRegionsWithSealedRetrofit(region)){
                is PokemonResult.Success ->{
                    showLoading.postValue(false)
                    msgError.postValue("")
                    pokemonList.postValue(result.data.pokemonList)
                }
                is PokemonResult.Failure->{
                    showLoading.postValue(false)
                    msgError.postValue(result.exception.message)
                }

                is PokemonResult.Loading -> {}
            }
        }
    }


    /**
     *Funcion para obetener el response de retrofit con emit y livedata desde el repository
     * */
    fun generationResponse(region :Int) = liveData(Dispatchers.IO){
        emit(PokemonResult.Loading())
        try {
            emit(PokemonRepository.getInstance().getRegionsWithSealedRetrofit(region))
        }catch (e: Exception){
            emit(PokemonResult.Failure(e))
        }
    }
}