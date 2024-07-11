package com.example.burgershub.data.repository

import com.example.burgershub.data.api.ServiceAPI
import com.example.burgershub.data.model.BurgerResponse
import com.example.burgershub.domain.repository.BurgerRepository
import javax.inject.Inject

class BurgerRepositoryImpl @Inject constructor(
    private val serviceAPI: ServiceAPI
) : BurgerRepository {

    override suspend fun burgers(): List<BurgerResponse> {
        return serviceAPI.burgers()
    }

    override suspend fun burgerById(burgerId: Int): BurgerResponse {
        return serviceAPI.burgersById(burgerId)
    }

    override suspend fun searchBurgers(search: String): List<BurgerResponse> {
        return serviceAPI.searchBurgers(search)
    }
}