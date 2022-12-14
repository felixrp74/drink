package com.example.drinks.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.drinks.data.vo.AppDatabase
import com.example.drinks.data.source.remote.DataSourceImpl
import com.example.drinks.data.model.Drink
import com.example.drinks.data.model.DrinkEntity
import com.example.drinks.databinding.FragmentDetailDrinkBinding
import com.example.drinks.data.source.RepoImpl
import com.example.drinks.ui.viewmodel.MainViewModel
import com.example.drinks.ui.viewmodel.VMFactory

class DrinkDetailFragment : Fragment() {

    private val viewModel by viewModels<MainViewModel> { VMFactory(
        RepoImpl(
        DataSourceImpl(
        AppDatabase.getInstance(requireActivity().applicationContext))
    )
    ) }

    private var _binding: FragmentDetailDrinkBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    private lateinit var drink: Drink

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireArguments().let {
            drink = it.getParcelable<Drink>("drink")!!
            Log.d("DETAIL_FRAGMENT", "gneio $drink")
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentDetailDrinkBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(requireContext()).load(drink.image).into(binding.imgDrink)
        binding.drinkTitle.text = drink.name
        binding.drinkDescription.text = drink.description

        binding.btnSaveDrink.setOnClickListener {
            viewModel.insertFavoriteDrink(
                DrinkEntity(
                    drink.idDrink,
                    drink.image,
                    drink.name,
                    drink.description
                )
            )

            Toast.makeText(requireContext(), " DRINK SAVED", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}