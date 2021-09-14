package com.dalvik.pokemonkotlin.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.dalvik.pokemonkotlin.R
import com.dalvik.pokemonkotlin.adapters.RegionFragmentAdapter
import com.dalvik.pokemonkotlin.databinding.ActivityMainBinding
import com.dalvik.pokemonkotlin.listeners.OnPokemonListener
import com.dalvik.pokemonkotlin.utils.Constants
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity(), OnPokemonListener {

    private lateinit var binding : ActivityMainBinding

    private lateinit var regionFragmentAdapter: RegionFragmentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        regionFragmentAdapter = RegionFragmentAdapter(this)
        binding.regionViewpager.adapter = regionFragmentAdapter


                /**
         * Cuando necesitemos agregar un listener a un viewpager y saber en que pagina esta
         *
         * */
       /* binding.regionViewpager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.regionName = getString(Constants.NAME_REGIONS_ARRAY[position])
            }
        })*/
        TabLayoutMediator(binding.tabsRegiones, binding.regionViewpager) { tab, position ->
            tab.text = getString(Constants.NAME_REGIONS_ARRAY[position])
        }.attach()

        binding.imgLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    override fun onPokemonCliked(numberPokemon: Int) {
        val intent= Intent(this,DetailPokemonActivity::class.java).apply {
            putExtra(Constants.PARAMETER_NUMBER_POKEMON,numberPokemon)
        }
        startActivity(intent)
    }
}