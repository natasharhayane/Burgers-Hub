package com.example.burgershub.presenter.burgers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.burgershub.databinding.FragmentBurgersBinding
import com.example.burgershub.domain.model.Burger
import com.example.burgershub.presenter.burgers.BurgersFragmentDirections
import com.example.burgershub.util.StateView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BurgersFragment : Fragment() {

    private var _binding: FragmentBurgersBinding? = null
    private val binding get() = _binding!!

    private val viewModel: BurgersViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBurgersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListeners()

        getBurgers()
    }

    private fun initListeners() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return if (query != null) {
                    searchBurger(query)
                    true
                } else false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        val closeButton: View? = binding.searchView.findViewById(androidx.appcompat.R.id.search_close_btn)
        closeButton?.setOnClickListener {
            binding.searchView.setQuery("", false)
            binding.searchView.clearFocus()
            getBurgers()
        }
    }

    private fun getBurgers() {
        viewModel.getBurgers().observe(viewLifecycleOwner) { stateView ->
            when (stateView) {
                is StateView.Loading -> {
                    showLoading(loading = true)
                }
                is StateView.Success -> {
                    showLoading(loading = false)

                    val burgers = stateView.data ?: emptyList()
                    initAdapter(burgers)
                }
                is StateView.Error -> {
                    showLoading(loading = false, error = true)
                    Toast.makeText(requireContext(), stateView.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun searchBurger(search: String) {
        viewModel.searchBurger(search).observe(viewLifecycleOwner) { stateView ->
            when (stateView) {
                is StateView.Loading -> {
                    showLoading(loading = true)
                    binding.progressBar.isVisible = true
                    binding.rvBurgers.isVisible = false
                }
                is StateView.Success -> {
                    showLoading(loading = false)

                    val burgers = stateView.data ?: emptyList()

                    if (burgers.isEmpty()) {
                        Toast.makeText(
                            requireContext(),
                            "Nenhum resultado encontrado.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    initAdapter(burgers)
                }
                is StateView.Error -> {
                    showLoading(loading = false, error = true)
                    Toast.makeText(requireContext(), stateView.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun showLoading(loading: Boolean, error: Boolean = false) {
        binding.progressBar.isVisible = loading && !error
        binding.rvBurgers.isVisible = !loading && !error
    }

    private fun initAdapter(burgers: List<Burger>) {
        with(binding.rvBurgers) {
            setHasFixedSize(true)
            adapter = BurgerAdapter(requireContext(), burgers) { burgerId ->
                val action = BurgersFragmentDirections
                    .actionBurgersFragmentToDetailsFragment(burgerId)
                findNavController().navigate(action)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}