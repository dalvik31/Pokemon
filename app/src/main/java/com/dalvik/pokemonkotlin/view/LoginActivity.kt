package com.dalvik.pokemonkotlin.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.dalvik.pokemonkotlin.R
import com.dalvik.pokemonkotlin.databinding.ActivityLoginBinding
import com.dalvik.pokemonkotlin.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity() {
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var bindinLoginBinding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindinLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        configureViewModel()
    }

    private fun configureViewModel() {
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        bindinLoginBinding.viewModel = loginViewModel
        bindinLoginBinding.lifecycleOwner = this
    }
}