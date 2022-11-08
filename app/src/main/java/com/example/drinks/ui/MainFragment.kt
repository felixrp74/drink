package com.example.drinks.ui

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.SearchView
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.drinks.data.vo.AppDatabase
import com.example.drinks.R
import com.example.drinks.data.source.remote.DataSourceImpl
import com.example.drinks.data.model.Drink
import com.example.drinks.databinding.FragmentMainBinding
import com.example.drinks.data.source.RepoImpl
import com.example.drinks.ui.viewmodel.MainViewModel
import com.example.drinks.ui.viewmodel.VMFactory
import com.example.drinks.data.vo.Resource

class MainFragment : Fragment(), MainAdapter.ItemClickListener {

    private val viewModel by viewModels<MainViewModel> { VMFactory(
        RepoImpl(
        DataSourceImpl(
        AppDatabase.getInstance(requireActivity().applicationContext))
    )
    ) }

    private var _binding: FragmentMainBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
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

//        binding.btnFavoriteDrinks.setOnClickListener {
//            findNavController().navigate(R.id.action_mainFragment2_to_favoriteFragment)
//        }

        //=========================================
        // The usage of an interface lets you inject your own implementation
        val menuHost: MenuHost = requireActivity()

        // Add menu items without using the Fragment Menu APIs
        // Note how we can tie the MenuProvider to the viewLifecycleOwner
        // and an optional Lifecycle.State (here, RESUMED) to indicate when
        // the menu should be visible
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                // Add menu items here
                menuInflater.inflate(R.menu.menubar, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                // Handle the menu selection
                when(menuItem.itemId){

                    R.id.action_favorite ->  {
                            findNavController().navigate(R.id.action_mainFragment2_to_favoriteFragment)


                    }

                }
                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
        //=========================================

        setSwipe()
    }

    private fun setSwipe(){
        binding.swipe.setOnRefreshListener{
            setupObservers()
        }
    }

    private fun setupObservers(){
        viewModel.fectchDrinkList.observe(viewLifecycleOwner) {

            when (it) {
                is Resource.Loading -> {
                    binding.pbCircle.visibility = View.VISIBLE
                    binding.swipe.isRefreshing = true
                }
                is Resource.Success -> {
                    binding.pbCircle.visibility = View.GONE
                    binding.swipe.isRefreshing = false
                    binding.rvDrink.adapter = MainAdapter(requireContext(), it.data, this)
                }
                is Resource.Failure -> {
                    binding.pbCircle.visibility = View.GONE
                    binding.swipe.isRefreshing = false
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

    override fun onClickDrink(drink: Drink, position:Int,itemView: View) {
        val bundle = Bundle()
        bundle.putParcelable("drink", drink)
        findNavController().navigate(R.id.action_mainFragment2_to_detailDrinkFragment2, bundle)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
/*
    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menubar, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        when(menuItem.itemId){

            R.id.action_favorite ->  binding.btnFavoriteDrinks.setOnClickListener {
                findNavController().navigate(R.id.action_mainFragment2_to_favoriteFragment)
            }

        }

        return true
    }
    */

}