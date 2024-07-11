package com.example.burgershub.data.mapper

import com.example.burgershub.data.model.BurgerResponse
import com.example.burgershub.data.model.ImageResponse
import com.example.burgershub.data.model.IngredientResponse
import com.example.burgershub.domain.model.Burger
import com.example.burgershub.domain.model.Image
import com.example.burgershub.domain.model.Ingredient

fun BurgerResponse.toDomain() = Burger( //transforma em objeto da camada de dominio
    id = this.id,
    name = this.name,
    images = this.images?.map { it.toDomain() },
    desc = this.desc,
    ingredients = this.ingredients?.map { it.toDomain() },
    price = this.price,
    veg = this.veg
)

fun ImageResponse.toDomain() = Image(
    sm = this.sm,
    lg = this.lg
)

fun IngredientResponse.toDomain() = Ingredient(
    id = this.id,
    name = this.name,
    img = this.img
)