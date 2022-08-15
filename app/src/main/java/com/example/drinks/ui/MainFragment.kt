package com.example.drinks.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.drinks.R
import com.example.drinks.data.model.DataSource
import com.example.drinks.databinding.FragmentMainBinding
import com.example.drinks.domain.RepoImpl
import com.example.drinks.ui.viewmodel.MainViewModel
import com.example.drinks.ui.viewmodel.VMFactory
import com.example.drinks.vo.Resource

class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel by viewModels<MainViewModel> { VMFactory(RepoImpl(DataSource())) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

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
        /*binding.btnDetailDrink.setOnClickListener {
            findNavController().navigate(R.id.detailDrinkFragment2)
        }*/
        setupRecyclerView()
        viewModel.fectchDrinkList.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                    binding.pbCircle.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.pbCircle.visibility = View.GONE
                    binding.rvDrink.adapter = MainAdapter(requireContext(), it.data)
                }
                is Resource.Failure -> {
                    binding.pbCircle.visibility = View.GONE
                    Toast.makeText(requireContext(), "Error ${it.exception}",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun setupRecyclerView() {
        binding.rvDrink.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}