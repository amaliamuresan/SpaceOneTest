package com.example.spaceonetest.presentation

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.spaceonetest.models.Product
import com.example.spaceonetest.presentation.viewmodels.DetailsViewModel

class DetailViewModelFactory(
    private val product: Product,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailsViewModel::class.java)) {
            return DetailsViewModel(product, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}