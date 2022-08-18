package com.example.drinks.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.drinks.R
import com.example.drinks.data.model.Drink
import com.example.drinks.databinding.FragmentDetailDrinkBinding
import com.example.drinks.databinding.FragmentMainBinding

class DrinkDetailFragment : Fragment() {
    private var _binding: FragmentDetailDrinkBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var drink:Drink

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireArguments().let {
            drink = it.getParcelable<Drink>("drink")!!
            Log.d("DETAIL_FRAGMENT", "gneio ${drink.toString()}")
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetailDrinkBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(requireContext()).load(drink.image).into(binding.imgDrink)
        binding.drinkTitle.text = drink.name
        binding.drinkDescription.text = drink.description

    }


}