package com.example.burgershub.domain.repository

import com.example.burgershub.data.model.BurgerResponse

interface BurgerRepository {

    suspend fun burgers(): List<BurgerResponse>

    suspend fun burgerById(burgerId: Int): BurgerResponse

    suspend fun searchBurgers(search: String): List<BurgerResponse>

}