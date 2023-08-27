package com.example.daggerbaseapiapplication.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.daggerbaseapiapplication.data.BaseRepo
import com.example.daggerbaseapiapplication.model.Product
import com.example.swipemvvmkoin.util.AppResult
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

// @Inject tells Dagger how to provide instances of this type
class MainViewModel @Inject constructor(private val repo: BaseRepo) : ViewModel() {
    val _productsFlow= MutableSharedFlow<AppResult<List<Product>>>()
    val productFlow =  _productsFlow.asSharedFlow()


    fun getProducts(){
        viewModelScope.launch {
            val products= repo.getProducts()
            _productsFlow.emit(products)
        }
    }
}