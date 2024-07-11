package com.example.burgershub.presenter.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.burgershub.data.model.ErrorResponse
import com.example.burgershub.domain.usecase.BurgerByIdUseCase
import com.example.burgershub.util.StateView
import com.example.burgershub.util.getErrorResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val burgerByIdUseCase: BurgerByIdUseCase
) : ViewModel() {

    fun getBurgers(burgerId: Int) = liveData(Dispatchers.IO) {
        try {
            emit(StateView.Loading())

            val burger = burgerByIdUseCase.invoke(burgerId)

            emit(StateView.Success(burger))
        } catch (ex: HttpException) {
            val error = ex.getErrorResponse<ErrorResponse>()
            emit(StateView.Error(error?.message))
        } catch (ex: Exception) {
            ex.printStackTrace()
            emit(StateView.Error(ex.message))
        }
    }

}