package com.example.burgershub.domain.model

data class Burger(
    val id: Int? = null,
    val name: String? = null,
    val images: List<Image>? = null,
    val desc: String? = null,
    val ingredients: List<Ingredient>? = null,
    val price: Float? = null,
    val veg: Boolean? = null
)
