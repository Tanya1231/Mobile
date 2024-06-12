package com.sf.healthylifestyle.view.confirm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.sf.healthylifestyle.domain.usecases.ConfirmUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class ConfirmViewModel(private val confirmUseCase: ConfirmUseCase) : ViewModel() {

    private var _isEntry = MutableSharedFlow<Boolean>()
    val isEntry: SharedFlow<Boolean>
        get() = _isEntry.asSharedFlow()

    fun confirm(code: String) {
        viewModelScope.launch {
            _isEntry.emit(confirmUseCase(code))
        }
    }

    class Factory(val confirmUseCase: ConfirmUseCase
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ConfirmViewModel::class.java)) {
                return ConfirmViewModel(confirmUseCase = confirmUseCase
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}