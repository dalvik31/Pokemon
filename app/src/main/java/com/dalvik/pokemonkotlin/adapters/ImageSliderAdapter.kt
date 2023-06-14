package com.dalvik.pokemonkotlin.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dalvik.pokemonkotlin.databinding.ItemContainerSliderImageBinding
import com.dalvik.pokemonkotlin.utils.Constants

class ImageSliderAdapter(private val idPokemon: String): RecyclerView.Adapter<ImageSliderAdapter.ImageSliderViewHolder>() {
    class ImageSliderViewHolder(private val itemContainerSliderImageBinding: ItemContainerSliderImageBinding ): RecyclerView.ViewHolder(itemContainerSliderImageBinding.root){
        fun bindSliderImage(imageUrl: String){
            itemContainerSliderImageBinding.imageUrl= imageUrl
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageSliderViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val ItemContainerSliderImageBinding = ItemContainerSliderImageBinding.inflate(layoutInflater,parent,false)
        return ImageSliderViewHolder(ItemContainerSliderImageBinding)
    }

    override fun onBindViewHolder(holder: ImageSliderViewHolder, position: Int) {
       val url =  if(Constants.IMAGES_URL[position].contains(".png")) Constants.IMAGES_URL[position] else
           Constants.IMAGES_URL[position].plus(idPokemon).plus(".png")
       holder.bindSliderImage(url)
    }

    override fun getItemCount(): Int {
        return Constants.IMAGES_URL.size
    }


}