package com.example.drinks.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.drinks.AppDatabase
import com.example.drinks.data.model.DataSource
import com.example.drinks.data.model.Drink
import com.example.drinks.databinding.FragmentFavoriteBinding
import com.example.drinks.domain.RepoImpl
import com.example.drinks.ui.viewmodel.MainViewModel
import com.example.drinks.ui.viewmodel.VMFactory
import com.example.drinks.vo.Resource

class FavoriteFragment : Fragment(), MainAdapter.ItemClickListener  {

    private val viewModel by viewModels<MainViewModel> { VMFactory(RepoImpl(DataSource(
        AppDatabase.getInstance(requireActivity().applicationContext))
    ))}

    private var _binding: FragmentFavoriteBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()
        setupRecyclerView()

    }

    private fun setupObservers(){

        viewModel.getFavoriteDrinkList().observe(viewLifecycleOwner) {

            when (it) {
                is Resource.Loading -> {
                    //binding.pbCircle.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    /*binding.pbCircle.visibility = View.GONE
                    binding.rvDrink.adapter = MainAdapter(requireContext(), it.data, this)*/
                    binding.pbFavoriteCircle.visibility = View.GONE

                    val drinkList = it.data.map {
                        Drink(it.idDrink,it.image,it.name,it.description)
                    }

                    binding.rvFavoriteDrink.adapter = MainAdapter(requireContext(), drinkList, this)

                    Log.d("FAVORITE_DRINK_LIST", "RUN APP ${ it.data.size}")
                }
                is Resource.Failure -> {
                    //binding.pbCircle.visibility = View.GONE
                    Toast.makeText(requireContext(), "Error ${it.exception}", Toast.LENGTH_SHORT)
                        .show()
                }
                else -> {
                    Toast.makeText(
                        requireContext(),
                        "ELSE",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

        }
    }

    private fun setupRecyclerView() {
        binding.rvFavoriteDrink.layoutManager = LinearLayoutManager(requireContext())
        binding.rvFavoriteDrink.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClickDrink(drink: Drink) {
        TODO("Not yet implemented")
    }

}