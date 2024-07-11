package com.example.burgershub.presenter.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.burgershub.R
import com.example.burgershub.databinding.FragmentDetailsBinding
import com.example.burgershub.domain.model.Burger
import com.example.burgershub.domain.model.Ingredient
import com.example.burgershub.util.StateView
import com.example.burgershub.util.formattedValue
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DetailsViewModel by viewModels()

    private val args: DetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getBurger()
    }

    private fun getBurger() {
        viewModel.getBurgers(args.burgerId).observe(viewLifecycleOwner) { stateView ->
            when (stateView) {
                is StateView.Loading -> {
                }
                is StateView.Success -> {
                    val burger = stateView.data
                    configData(burger)
                }
                is StateView.Error -> {
                    Toast.makeText(requireContext(), stateView.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun configData(burger: Burger?) {
        binding.textName.text = burger?.name
        binding.textDescription.text = burger?.desc
        binding.textPrice.text =
            getString(R.string.text_formatted_value, burger?.price?.formattedValue())

        Picasso
            .get()
            .load(burger?.images?.get(1)?.lg)
            .into(binding.imageBurger)

        initAdapter(burger?.ingredients ?: emptyList())
    }

    private fun initAdapter(ingredients: List<Ingredient>) {
        with(binding.rvIngredient) {
            setHasFixedSize(true)
            adapter = IngredientAdapter(ingredients)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}