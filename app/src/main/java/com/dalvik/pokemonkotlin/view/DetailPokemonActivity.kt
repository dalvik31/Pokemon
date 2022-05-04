package com.dalvik.pokemonkotlin.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.dalvik.pokemonkotlin.R
import com.dalvik.pokemonkotlin.adapters.ImageSliderAdapter
import com.dalvik.pokemonkotlin.databinding.ActivityDetailPokemonBinding
import com.dalvik.pokemonkotlin.utils.Constants
import com.dalvik.pokemonkotlin.viewmodel.PokemonDetailViewModel

class DetailPokemonActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailPokemonBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_pokemon);
        downloadPokemonDetail()
    }

    private fun downloadPokemonDetail() {
        val pokemonDetailViewModel = ViewModelProvider(this).get(PokemonDetailViewModel::class.java)
        pokemonDetailViewModel.getPokemonDetail(getPokemonNumberExtras()) { response ->
            if (response == null) {
                Toast.makeText(this,"No hay informacion de este pokemon", Toast.LENGTH_SHORT).show()
                finish()
                return@getPokemonDetail
            }
            binding.contentInformation.visibility = View.VISIBLE
            binding.pokemon = response
            binding.sliderViewPager.offscreenPageLimit = 1
            binding.sliderViewPager.adapter = ImageSliderAdapter(response.id.toString())
        }
    }

    private fun getPokemonNumberExtras(): Int {
        intent.extras?.let {
            return it.getInt(Constants.PARAMETER_NUMBER_POKEMON)
        }
        return 1
    }

}