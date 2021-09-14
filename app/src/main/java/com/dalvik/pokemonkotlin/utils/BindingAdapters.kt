package com.dalvik.pokemonkotlin.utils

import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.dalvik.pokemonkotlin.R
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class BindingAdapters {
    companion object {
        @JvmStatic
        @BindingAdapter("android:imageURL")
        fun setImageUrl(imageView: ImageView, url: String?) {
            changeImageUrl(imageView, url)
        }


        @JvmStatic
        @BindingAdapter("android:imagePath")
        fun setImageBackground(imageView: ImageView, pokemonType: String?) {
            changeImagePath(imageView, getImageBackground(pokemonType))
        }

        @JvmStatic
        @BindingAdapter("android:imageType")
        fun setImagePath(imageView: ImageView, pokemonType: String?) {
            changeImagePath(imageView, getImageType(pokemonType))
        }

        @JvmStatic
        @BindingAdapter("android:colorScrim")
        fun setColor(collapsingToolbarLayout: CollapsingToolbarLayout, pokemonType: String?) {
            collapsingToolbarLayout.statusBarScrim =
                ContextCompat.getDrawable(
                    PokemonApplication.instance,
                    getColor(pokemonType)
                )
            collapsingToolbarLayout.contentScrim =
                ContextCompat.getDrawable(
                    PokemonApplication.instance,
                    getColor(pokemonType)
                )
        }


        private fun changeImageUrl(imageView: ImageView, url: String?) {
            try {
                imageView.alpha = 0f
                Picasso.get().load(url).noFade().into(imageView, object : Callback {
                    override fun onSuccess() {
                        imageView.animate().setDuration(300).alpha(1f).start()
                    }

                    override fun onError(e: Exception) {}
                })
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        private fun changeImagePath(imageView: ImageView, url: Int) {
            try {
                imageView.alpha = 0f
                Picasso.get().load(url).noFade().into(imageView, object : Callback {
                    override fun onSuccess() {
                        imageView.animate().setDuration(300).alpha(1f).start()
                    }

                    override fun onError(e: Exception) {}
                })
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }


        private fun getImageType(pokemonType: String?): Int {
            return if (pokemonType == null) R.color.purple_700 else when (pokemonType) {
                Constants.TYPE_BUG -> R.drawable.ic_bug
                Constants.TYPE_DARK -> R.drawable.ic_dark
                Constants.TYPE_DRAGON -> R.drawable.ic_dragon
                Constants.TYPE_ELECTRIC -> R.drawable.ic_electric
                Constants.TYPE_FAIRY -> R.drawable.ic_fairy
                Constants.TYPE_FIGHTING -> R.drawable.ic_figthing
                Constants.TYPE_FIRE -> R.drawable.ic_fire
                Constants.TYPE_FLYING -> R.drawable.ic_flying
                Constants.TYPE_GHOST -> R.drawable.ic_ghost
                Constants.TYPE_GRASS -> R.drawable.ic_grass
                Constants.TYPE_GROUND -> R.drawable.ic_ground
                Constants.TYPE_ICE -> R.drawable.ic_ice
                Constants.TYPE_NORMAL -> R.drawable.ic_normal
                Constants.TYPE_PISCHIC -> R.drawable.ic_physic
                Constants.TYPE_POISON -> R.drawable.ic_poison
                Constants.TYPE_ROCK -> R.drawable.ic_rock
                Constants.TYPE_STEEL -> R.drawable.ic_steel
                Constants.TYPE_WATER -> R.drawable.ic_water
                else -> R.color.purple_700
            }
        }

        fun getImageBackground(pokemonType: String?): Int {
            return if (pokemonType == null) R.color.purple_700 else when (pokemonType) {
                Constants.TYPE_BUG -> R.drawable.background_bug
                Constants.TYPE_DARK -> R.drawable.background_dark
                Constants.TYPE_DRAGON -> R.drawable.background_dragon
                Constants.TYPE_ELECTRIC -> R.drawable.background_electric
                Constants.TYPE_FAIRY -> R.drawable.background_fairy
                Constants.TYPE_FIGHTING -> R.drawable.background_fighting
                Constants.TYPE_FIRE -> R.drawable.background_fire
                Constants.TYPE_FLYING -> R.drawable.background_flying
                Constants.TYPE_GHOST -> R.drawable.background_ghost
                Constants.TYPE_GRASS -> R.drawable.background_grass
                Constants.TYPE_GROUND -> R.drawable.background_ground
                Constants.TYPE_ICE -> R.drawable.background_ice
                Constants.TYPE_NORMAL -> R.drawable.background_normal
                Constants.TYPE_PISCHIC -> R.drawable.background_pychic
                Constants.TYPE_POISON -> R.drawable.background_poison
                Constants.TYPE_ROCK -> R.drawable.background_rock
                Constants.TYPE_STEEL -> R.drawable.background_steel
                Constants.TYPE_WATER -> R.drawable.background_water
                else -> R.color.teal_700
            }
        }

        private fun getColor(pokemonType: String?): Int {
            return if (pokemonType == null) R.color.purple_700 else when (pokemonType) {
                Constants.TYPE_BUG -> R.color.bug_type
                Constants.TYPE_DARK -> R.color.dark_type
                Constants.TYPE_DRAGON -> R.color.dragon_type
                Constants.TYPE_ELECTRIC -> R.color.electric_type
                Constants.TYPE_FAIRY -> R.color.fairy_type
                Constants.TYPE_FIGHTING -> R.color.fighting_type
                Constants.TYPE_FIRE -> R.color.fire_type
                Constants.TYPE_FLYING -> R.color.flying_type
                Constants.TYPE_GHOST -> R.color.ghost_type
                Constants.TYPE_GRASS -> R.color.grass_type
                Constants.TYPE_GROUND -> R.color.ground_type
                Constants.TYPE_ICE -> R.color.ice_type
                Constants.TYPE_NORMAL -> R.color.normal_type
                Constants.TYPE_PISCHIC -> R.color.psychic_type
                Constants.TYPE_POISON -> R.color.poison_type
                Constants.TYPE_ROCK -> R.color.rock_type
                Constants.TYPE_STEEL -> R.color.steel_type
                Constants.TYPE_WATER -> R.color.water_type
                else -> R.color.teal_700
            }
        }
    }
}