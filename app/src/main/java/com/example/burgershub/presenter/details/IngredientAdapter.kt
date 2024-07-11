package com.example.burgershub.presenter.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.burgershub.databinding.BurgerItemBinding
import com.example.burgershub.databinding.IngredientItemBinding
import com.example.burgershub.domain.model.Ingredient
import com.squareup.picasso.Picasso

class IngredientAdapter(
    private val ingredients: List<Ingredient>
) : RecyclerView.Adapter<IngredientAdapter.MyViewHolder>() {

    inner class MyViewHolder(val binding: IngredientItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            IngredientItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = ingredients.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val ingredient = ingredients[position]

        holder.binding.textName.text = ingredient.name

        Picasso
            .get()
            .load(ingredient.img)
            .into(holder.binding.imageBurger)
    }

}