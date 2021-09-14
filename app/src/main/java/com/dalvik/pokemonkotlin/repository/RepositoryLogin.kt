package com.dalvik.pokemonkotlin.repository

import com.dalvik.pokemonkotlin.BuildConfig
import com.dalvik.pokemonkotlin.models.RequestLogin
import com.dalvik.pokemonkotlin.models.ResponseLogin
import com.dalvik.pokemonkotlin.network.LoginResult
import com.dalvik.pokemonkotlin.network.RetrofitBuilder
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL

class RepositoryLogin {
    companion object {
        private var INSTANCE: RepositoryLogin? = null
        fun getInstance() = INSTANCE ?: RepositoryLogin().also {
            INSTANCE = it
        }
    }


    // CREATE NEW SESSION WITH RETROFIT
    fun getLogin(
        requestLogin: RequestLogin,
        onResult: (isSuccess: Boolean, response: ResponseLogin?, error: String) -> Unit
    ) {

        RetrofitBuilder.apiServiceLogin.createSession(requestLogin)
            .enqueue(object : Callback<ResponseLogin> {
                override fun onResponse(
                    call: Call<ResponseLogin>,
                    response: Response<ResponseLogin>
                ) {
                    if (response.isSuccessful) {
                        onResult(true, response.body(), "")
                    } else {
                        onResult(false, null, "Error al leer la respuesta")
                    }
                }

                override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {
                    onResult(false, null, "Error al leer la respuesta: ${t.message}")
                }

            })


    }

    // GET REQUEST NEW TOKEN
    fun getCreateToken(
        onResult: (isSuccess: Boolean, response: ResponseLogin?, error: String) -> Unit
    ) {

        RetrofitBuilder.apiServiceLogin.generateToken()
            .enqueue(object : Callback<ResponseLogin> {
                override fun onResponse(
                    call: Call<ResponseLogin>,
                    response: Response<ResponseLogin>
                ) {
                    if (response.isSuccessful) {
                        onResult(true, response.body(), "")
                    } else {
                        onResult(false, null, "Error al leer la respuesta")
                    }
                }

                override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {
                    onResult(false, null, "Error al leer la respuesta: ${t.message}")
                }

            })
    }


    // GET REQUEST NEW SESION ID
    fun getSessionId(requestToken: String,
        onResult: (isSuccess: Boolean, response: ResponseLogin?, error: String) -> Unit
    ) {
        RetrofitBuilder.apiServiceLogin.getSessionId(requestToken)
            .enqueue(object : Callback<ResponseLogin> {
                override fun onResponse(
                    call: Call<ResponseLogin>,
                    response: Response<ResponseLogin>
                ) {

                    if (response.isSuccessful) {
                        onResult(true, response.body(), "")
                    } else {
                        onResult(false, null, "Error al leer la respuesta")
                    }
                }

                override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {
                    onResult(false, null, "Error al leer la respuesta: ${t.message}")
                }

            })
    }


    // CREATE NEW SESSION with HTTPURLCONNECTION
    fun getLoginHttpConnection(requestLogin: RequestLogin): LoginResult {
        val stringRequest = Gson().toJson(requestLogin)
        val baseUrl = "https://api.themoviedb.org/3/authentication/token/validate_with_login?api_key=${BuildConfig.API_KEY_TMBD}"
      val url = URL(baseUrl)
         (url.openConnection() as? HttpURLConnection)?.run {
             requestMethod = "POST"
             setRequestProperty("Content-Type", "application/json")
             doInput = true
             doOutput = true
             val outputStreamWriter = OutputStreamWriter(outputStream)
             outputStreamWriter.write(stringRequest)
             outputStreamWriter.flush()

             return if (responseCode == HttpURLConnection.HTTP_OK) {
                 val response = inputStream.bufferedReader()
                     .use { it.readText() }
                 LoginResult.Success(Gson().fromJson(response, ResponseLogin::class.java))

             } else
                 LoginResult.Failure(Exception("Error al leer la respuesta"))
         }
         return LoginResult.Failure(Exception("Cannot open HttpURLConnection"))
    }




}