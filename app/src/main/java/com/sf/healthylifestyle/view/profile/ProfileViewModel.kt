package com.sf.healthylifestyle.view.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ProfileViewModel() : ViewModel() {

    class Factory(
  //      val listOrders: ListOrders,
   //     val listAddresses: ListAddresses,
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
                return ProfileViewModel(
  //                  listOrders = listOrders,
                //                  listAddresses = listAddresses
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}