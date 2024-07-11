package com.example.burgershub.presenter.burgers

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.burgershub.R
import com.example.burgershub.databinding.BurgerItemBinding
import com.example.burgershub.domain.model.Burger
import com.example.burgershub.util.formattedValue
import com.squareup.picasso.Picasso

class BurgerAdapter(
    private val context: Context,
    private val burgers: List<Burger>,
    private val onClick: (Int) -> Unit
) : RecyclerView.Adapter<BurgerAdapter.MyViewHolder>() {

    inner class MyViewHolder(val binding: BurgerItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            BurgerItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = burgers.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val burger = burgers[position]

        holder.binding.textName.text = burger.name
        holder.binding.textDescription.text = burger.desc
        holder.binding.textPrice.text =
            context.getString(R.string.text_formatted_value, burger.price?.formattedValue())

        Picasso
            .get()
            .load(burger.images?.get(1)?.lg)
            .into(holder.binding.imageBurger)

        holder.itemView.setOnClickListener { onClick(burger.id ?: 0) }
    }

}