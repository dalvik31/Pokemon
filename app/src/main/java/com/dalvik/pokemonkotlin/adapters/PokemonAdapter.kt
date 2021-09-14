package com.dalvik.pokemonkotlin.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dalvik.pokemonkotlin.databinding.ItemPokemonBinding
import com.dalvik.pokemonkotlin.listeners.OnPokemonListener
import com.dalvik.pokemonkotlin.models.Pokemon

class PokemonAdapter(private var pokemonList: List<Pokemon>, private  val pokemonListener: OnPokemonListener) : RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {

    class PokemonViewHolder(private val itemPokemonBinding: ItemPokemonBinding): RecyclerView.ViewHolder(itemPokemonBinding.root){
        fun bindPokemon(pokemon: Pokemon, pokemonListener: OnPokemonListener){
            itemPokemonBinding.pokemon = pokemon
            itemPokemonBinding.root.setOnClickListener {
                pokemonListener.onPokemonCliked(pokemon.getNumber())
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemPokemonBinding = ItemPokemonBinding.inflate(layoutInflater,parent,false)
        return PokemonViewHolder(itemPokemonBinding)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.bindPokemon(pokemonList[position],pokemonListener)
    }

    override fun getItemCount(): Int {
       return pokemonList.size
    }

    fun updateData(pokemonList: List<Pokemon>){
        val sortedList = pokemonList.sortedWith( compareBy { it.getNumber() })
        this.pokemonList = sortedList
        notifyDataSetChanged()
    }
}