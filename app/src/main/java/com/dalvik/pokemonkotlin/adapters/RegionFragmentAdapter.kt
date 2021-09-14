package com.dalvik.pokemonkotlin.adapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dalvik.pokemonkotlin.fragments.RegionFragment
import com.dalvik.pokemonkotlin.utils.Constants

class RegionFragmentAdapter (fragment: FragmentActivity): FragmentStateAdapter(fragment){
    override fun getItemCount(): Int {
        return Constants.TOTAL_REGIONS
    }

    override fun createFragment(position: Int): Fragment {
        // Return a NEW fragment instance in createFragment(int)
        val fragment = RegionFragment()
        val args = Bundle()
        args.putInt(Constants.PARAMETER_REGION_NAME, position + 1)
        fragment.arguments = args
        return fragment
    }
}