package com.sf.healthylifestyle.view.basket

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.sf.healthylifestyle.data.dto.product.response.ProductResponse
import com.sf.healthylifestyle.domain.models.Dish
import com.sf.healthylifestyle.domain.usecases.DelBasketUseCase
import com.sf.healthylifestyle.domain.usecases.GetBasketUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class BasketViewModel(
    private val getBasketUseCase: GetBasketUseCase,
    private val delBasketUseCase: DelBasketUseCase
) : ViewModel() {

    private var _dishes = MutableSharedFlow<List<Dish>>()
    val dishes: SharedFlow<List<Dish>>
        get() = _dishes.asSharedFlow()

    init {
        println("Init BasketViewModel")
        getBasket()
    }

    fun getBasket(){
        viewModelScope.launch {
            _dishes.emit(getBasketUseCase())
        }
    }

    fun delBasket(){
        viewModelScope.launch {
            delBasketUseCase()
        }
    }


    class Factory(
        private val getBasketUseCase: GetBasketUseCase,
        private val delBasketUseCase: DelBasketUseCase
        ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(BasketViewModel::class.java)) {
                return BasketViewModel(
                    getBasketUseCase = getBasketUseCase,
                    delBasketUseCase = delBasketUseCase
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}