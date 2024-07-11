package com.example.burgershub.data.model

data class BurgerResponse(
    val id: Int? = null,
    val name: String? = null,
    val images: List<ImageResponse>? = null,
    val desc: String? = null,
    val ingredients: List<IngredientResponse>? = null,
    val price: Float? = null,
    val veg: Boolean? = null
)
