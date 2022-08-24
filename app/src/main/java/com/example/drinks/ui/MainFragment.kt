package com.example.drinks.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.drinks.AppDatabase
import com.example.drinks.R
import com.example.drinks.data.model.DataSourceImpl
import com.example.drinks.data.model.Drink
import com.example.drinks.databinding.FragmentMainBinding
import com.example.drinks.domain.RepoImpl
import com.example.drinks.ui.viewmodel.MainViewModel
import com.example.drinks.ui.viewmodel.VMFactory
import com.example.drinks.vo.Resource

class MainFragment : Fragment(), MainAdapter.ItemClickListener {

    private val viewModel by viewModels<MainViewModel> { VMFactory(RepoImpl(DataSourceImpl(
        AppDatabase.getInstance(requireActivity().applicationContext)))) }

    private var _binding: FragmentMainBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupSearchView()
        setupObservers()

        binding.btnFavoriteDrinks.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment2_to_favoriteFragment)
        }


    }

    private fun setupObservers(){
        viewModel.fectchDrinkList.observe(viewLifecycleOwner) {

            when (it) {
                is Resource.Loading -> {
                    binding.pbCircle.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.pbCircle.visibility = View.GONE
                    binding.rvDrink.adapter = MainAdapter(requireContext(), it.data, this)
                }
                is Resource.Failure -> {
                    binding.pbCircle.visibility = View.GONE
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

    private fun setupSearchView(){
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Log.d("SEARCH_DRINK","$query")
                viewModel.setTrago(query!!)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                Log.i("TAG","Llego al querytextchange")
                return true
            }
        })
    }

    private fun setupRecyclerView() {
        binding.rvDrink.layoutManager = LinearLayoutManager(requireContext())
        binding.rvDrink.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
    }

    override fun onClickDrink(drink: Drink, position:Int) {
        val bundle = Bundle()
        bundle.putParcelable("drink", drink)
        findNavController().navigate(R.id.action_mainFragment2_to_detailDrinkFragment2, bundle)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}