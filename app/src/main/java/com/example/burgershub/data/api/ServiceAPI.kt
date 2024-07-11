package com.example.burgershub.data.api

import com.example.burgershub.data.model.BurgerResponse
import io.github.brunogabriel.mockpinterceptor.MOCK
import retrofit2.http.*

interface ServiceAPI {

    @MOCK(asset = "burgers_response.json", runDelay = true, statusCode = 200)
    @GET("burgers")
    suspend fun burgers(): List<BurgerResponse>

    @MOCK(asset = "burger_response.json", runDelay = true)
    @GET("burgers/{burger_id}")
    suspend fun burgersById(
        @Path("burger_id") burgerId: Int
    ): BurgerResponse

    @MOCK(asset = "burgers_response.json", runDelay = true)
    @GET("find-burger/")
    suspend fun searchBurgers(
        @Query("search") search: String
    ): List<BurgerResponse>

}