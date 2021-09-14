package com.dalvik.pokemonkotlin.viewmodel

import android.text.Editable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dalvik.pokemonkotlin.models.RequestLogin
import com.dalvik.pokemonkotlin.network.LoginResult
import com.dalvik.pokemonkotlin.network.PokemonResult
import com.dalvik.pokemonkotlin.repository.PokemonRepository
import com.dalvik.pokemonkotlin.repository.RepositoryLogin
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    val msgResponse = MutableLiveData("")
    var newToken = ""

    /**
     * Funcion para obetener el response de retrofit con un callback desde el repository
     * */
    fun getLogin(userName: Editable,password: Editable) {
        /**
         * GetLogin with httpurlconnection method POST
         * */
        viewModelScope.launch(Dispatchers.IO) {
            when(val response = RepositoryLogin.getInstance().getLoginHttpConnection(RequestLogin(userName.toString(), password.toString(), newToken))){
                is LoginResult.Success ->  {
                    msgResponse.postValue("Sesion iniciada correctamente")
                }
                is LoginResult.Failure ->{
                    msgResponse.postValue(response.errorResponse.message)
                }
            }

        }

        /**
         * GetLogin with retrofit method POST
         * */
       /* RepositoryLogin.getInstance().getLogin(RequestLogin(userName.toString(), password.toString(), newToken)) { isSuccess, response, error ->
            if (isSuccess && response != null) {
                msgResponse.postValue("Sesion iniciada correctamente")
            } else {
                msgResponse.postValue(error)
            }
        }*/
    }

    /**
     * Funcion para obetener el response de retrofit con un callback desde el repository
     * */
    fun generateToken() {
        RepositoryLogin.getInstance().getCreateToken() { isSuccess, response, error ->
            if (isSuccess && response != null) {
                newToken =  response.requestToque
                msgResponse.postValue(newToken)
            } else {
                msgResponse.postValue(error)
            }
        }
    }

    /**
     * Funcion para obetener el response de retrofit con un callback desde el repository
     * */
    fun getSessionId() {
        RepositoryLogin.getInstance().getSessionId(newToken) { isSuccess, response, error ->
            if (isSuccess && response != null) {
                msgResponse.postValue(newToken)
            } else {
                msgResponse.postValue(error)
            }
        }
    }

    fun validateEdittext(userName: Editable,password: Editable): Boolean{
        return userName.length > 3 && password.length > 3
    }
}