package com.example.spaceonetest.presentation.viewmodels

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.spaceonetest.models.Category
import com.example.spaceonetest.models.Product
import com.example.spaceonetest.service.SpaceOneApi
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class OverviewViewModel : ViewModel() {
    private val _products = MutableLiveData<List<Product>>()


   companion object {
       private val _categories  = MutableLiveData<List<Category>>()
       val categories : LiveData<List<Category>>
           get() = _categories
   }

    private val _navigateToSelectedProduct = MutableLiveData<Product?>()

    val navigateToSelectedProduct: MutableLiveData<Product?>
        get() = _navigateToSelectedProduct

    init {
        geAllProducts()
        geAllCategories()
    }

    val products: LiveData<List<Product>>
        get() = _products

    private fun geAllProducts() {
        SpaceOneApi.retrofitService.getAllProducts().enqueue(object : Callback<List<Product>> {
            override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                Log.d("OverviewViewModel", "Failure - getAllProducts()")
            }

            override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {
                _products.value = response.body()
            }
        })
    }

    private fun geAllCategories() {
        SpaceOneApi.retrofitService.getAllCategories().enqueue(object : Callback<List<Category>> {
            override fun onFailure(call: Call<List<Category>>, t: Throwable) {
                Log.d("OverviewViewModel", "Failure - getAllCategories()")
            }

            override fun onResponse(call: Call<List<Category>>, response: Response<List<Category>>) {
                _categories.value = response.body()
                _categories.value?.get(0)?.let { Log.d("OverviewViewModel", it.name) }
            }
        })
    }



    fun displayProductDetails(product: Product) {
        _navigateToSelectedProduct.value = product
    }

    fun displayProductDetailsDone() {
        _navigateToSelectedProduct.value = null
    }




}

