package com.sf.healthylifestyle.view.confirm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ConfirmViewModel : ViewModel() {
    class Factory(
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ConfirmViewModel::class.java)) {
                return ConfirmViewModel(
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}