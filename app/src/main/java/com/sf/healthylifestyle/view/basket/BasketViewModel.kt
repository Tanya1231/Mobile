package com.sf.healthylifestyle.view.basket

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.sf.healthylifestyle.data.dto.product.response.ProductResponse
import com.sf.healthylifestyle.domain.models.Dish
import com.sf.healthylifestyle.domain.usecases.DelBasketUseCase
import com.sf.healthylifestyle.domain.usecases.DelDishFromBasketUseCase
import com.sf.healthylifestyle.domain.usecases.GetBasketUseCase
import com.sf.healthylifestyle.domain.usecases.GetDishByIdUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BasketViewModel(
    private val getBasketUseCase: GetBasketUseCase,
    private val delBasketUseCase: DelBasketUseCase,
    private val delDishFromBasketUseCase: DelDishFromBasketUseCase,
//    private val getDishByIdUseCase: GetDishByIdUseCase,
) : ViewModel() {

    private var _dishes = MutableSharedFlow<List<Dish>>()
    val dishes: SharedFlow<List<Dish>>
        get() = _dishes.asSharedFlow()

//    private var _dish = MutableSharedFlow<Dish>()
//    val dish: SharedFlow<Dish>
//        get() = _dish.asSharedFlow()

    init {
        println("Init BasketViewModel")
        getBasket()
    }

    fun getBasket() {
        viewModelScope.launch {
            _dishes.emit(getBasketUseCase())
        }
    }

    fun delBasket() {
        viewModelScope.launch {
            delBasketUseCase()
        }
    }

    fun delDish(id: Int) {
        viewModelScope.launch {
            val result = async {
                delDishFromBasketUseCase(id)
            }
            result.await()
            async {
                _dishes.emit(getBasketUseCase())
            }
//            val dishesList = dishes.await()
        }
    }

//    fun getDish(id: Int){
//        viewModelScope.launch {
//            val dish = getDishByIdUseCase(id)
//            if (dish!=null) _dish.emit(dish)
//        }
//    }


    class Factory(
        private val getBasketUseCase: GetBasketUseCase,
        private val delBasketUseCase: DelBasketUseCase,
        private val delDishFromBasketUseCase: DelDishFromBasketUseCase,
//        private val getDishByIdUseCase: GetDishByIdUseCase,
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(BasketViewModel::class.java)) {
                return BasketViewModel(
                    getBasketUseCase = getBasketUseCase,
                    delBasketUseCase = delBasketUseCase,
                    delDishFromBasketUseCase = delDishFromBasketUseCase,
//                    getDishByIdUseCase = getDishByIdUseCase
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}