package com.sf.healthylifestyle.view.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.sf.healthylifestyle.domain.models.Dish
import com.sf.healthylifestyle.domain.usecases.GetDishByIdUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class DescriptionDishViewModel(private val getDishByIdUseCase: GetDishByIdUseCase,) : ViewModel() {
    private var _dish = MutableSharedFlow<Dish>()
    val dish: SharedFlow<Dish>
        get() = _dish.asSharedFlow()

    fun getDish(id: Int){
        viewModelScope.launch {
            val dish = getDishByIdUseCase(id)
            if (dish!=null) _dish.emit(dish)
        }
    }

    class Factory(
        private val getDishByIdUseCase: GetDishByIdUseCase,
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(DescriptionDishViewModel::class.java)) {
                return DescriptionDishViewModel(
                    getDishByIdUseCase = getDishByIdUseCase
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}