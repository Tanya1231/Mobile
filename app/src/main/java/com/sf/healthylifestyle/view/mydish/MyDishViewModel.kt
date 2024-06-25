package com.sf.healthylifestyle.view.mydish

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.sf.healthylifestyle.data.dto.product.response.ProductResponse
import com.sf.healthylifestyle.domain.usecases.AddDishUseCase
import com.sf.healthylifestyle.domain.usecases.GetLeftHalfUseCase
import com.sf.healthylifestyle.domain.usecases.GetRightHalfUseCase
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class MyDishViewModel(
    private val getLeftHalfUseCase: GetLeftHalfUseCase,
    private val getRightHalfUseCase: GetRightHalfUseCase,
    private val addDishUseCase: AddDishUseCase
) : ViewModel() {

    private var _pairHalfesDishes =
        MutableSharedFlow<Pair<List<ProductResponse>, List<ProductResponse>>>()
    val pairHalfesDishes: SharedFlow<Pair<List<ProductResponse>, List<ProductResponse>>>
        get() = _pairHalfesDishes.asSharedFlow()

    init {
        println("Init MyDishViewModel")
        getTwoHalfes()
    }

    fun getTwoHalfes() {
        viewModelScope.launch {
            val leftHalf = async { getLeftHalfUseCase() }
            val rightHalf = async { getRightHalfUseCase() }

            val leftHalfResult = leftHalf.await()
            val rightHalfResult = rightHalf.await()

            if (leftHalfResult != null && rightHalfResult != null)
                _pairHalfesDishes.emit(Pair(leftHalfResult, rightHalfResult))
        }
    }

    fun addDishToBasket(dish: Pair<ProductResponse?, ProductResponse?>) {
        println("MyDishViewModel -> left: ${dish.first}; right ${dish.second}")
        viewModelScope.launch {
            val leftHalf = async {
                dish.first?.let{addDishUseCase(it)}
            }
            val rightHalf = async {
                dish.second?.let{addDishUseCase(it)}
            }

            leftHalf.await()
            rightHalf.await()
        }
    }

    class Factory(
        private val getLeftHalfUseCase: GetLeftHalfUseCase,
        private val getRightHalfUseCase: GetRightHalfUseCase,
        private val addDishUseCase: AddDishUseCase
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MyDishViewModel::class.java)) {
                return MyDishViewModel(
                    getLeftHalfUseCase = getLeftHalfUseCase,
                    getRightHalfUseCase = getRightHalfUseCase,
                    addDishUseCase = addDishUseCase
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}