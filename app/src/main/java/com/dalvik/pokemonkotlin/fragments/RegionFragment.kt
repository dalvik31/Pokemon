package com.dalvik.pokemonkotlin.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dalvik.pokemonkotlin.R
import com.dalvik.pokemonkotlin.adapters.PokemonAdapter
import com.dalvik.pokemonkotlin.databinding.RegionFragmentBinding
import com.dalvik.pokemonkotlin.listeners.OnPokemonListener
import com.dalvik.pokemonkotlin.models.Pokemon
import com.dalvik.pokemonkotlin.network.PokemonResult
import com.dalvik.pokemonkotlin.utils.Constants
import com.dalvik.pokemonkotlin.viewmodel.RegionViewModel

class RegionFragment : Fragment() {

    private lateinit var binding: RegionFragmentBinding
    private lateinit var viewModel: RegionViewModel
    private lateinit var pokemonAdapter: PokemonAdapter
    private lateinit var pokemonList: List<Pokemon>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.region_fragment, container, false);
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(RegionViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        doInitialization()
        getBundleExtra()
    }

    private fun doInitialization() {
        pokemonList = emptyList()
        pokemonAdapter = PokemonAdapter(pokemonList, activity as OnPokemonListener)
        binding.recyclerviewRegion.adapter = pokemonAdapter
    }

    private fun getBundleExtra() {
        arguments?.let { bundle ->

            /**
             * Funcion para obetener el response de retrofit con un callback desde el viewmodel
             * */
            /*viewModel.getGeneration(bundle.getInt(Constants.PARAMETER_REGION_NAME)) { response ->
                pokemonAdapter.updateData(response!!.pokemonList)
            }
            */

            //*******************************************************


            /**
             *Funcion para obetener el response de HttpURLConnection con sealed class desde el viewmodel
             * con un observer en la lista
             * */

           /* viewModel.getGenerationSealedHttp(bundle.getInt(Constants.PARAMETER_REGION_NAME))
            viewModel.pokemonList.observe(viewLifecycleOwner, {
                pokemonAdapter.updateData(it)
            })*/

            //*******************************************************




            /**
             *Funcion para obetener el response de Retrofit con sealed class desde el viewmodel
             * con un observer en la lista
             * */

             /*viewModel.getGenerationSealedRetrofit(bundle.getInt(Constants.PARAMETER_REGION_NAME))
             viewModel.pokemonList.observe(viewLifecycleOwner, {
                 pokemonAdapter.updateData(it)
             })*/

            //*******************************************************



            /**
             *Funcion para obetener el response de Retrofit con sealed class y emit desde el viewmodel
             * y la sealedclass desde el view (fragment)
             * */
            viewModel.generationResponse(bundle.getInt(Constants.PARAMETER_REGION_NAME)).observe(viewLifecycleOwner, {
                result ->
                when(result){
                    is PokemonResult.Loading ->{
                        viewModel.showLoading.postValue(true)
                    }
                    is PokemonResult.Success ->{
                        viewModel.showLoading.postValue(false)
                        viewModel.msgError.postValue("")
                        pokemonAdapter.updateData(result.data.pokemonList)
                    }
                    is PokemonResult.Failure ->{
                        viewModel.showLoading.postValue(false)
                        viewModel.msgError.postValue(result.exception.message)
                    }
                }
            })

            //*******************************************************



        } ?: run {
            /**
             * Aqui validamos si el valor bundle es nulo o si en el bundle no viene el valor
             * de la constante
             * **/
        }
    }
}