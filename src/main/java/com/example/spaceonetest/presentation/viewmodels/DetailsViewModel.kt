package com.example.spaceonetest.presentation.viewmodels

import android.app.Application
import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.spaceonetest.models.Product
import com.example.spaceonetest.service.SpaceOneApi
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailsViewModel(product: Product, application: Application) : ViewModel() {
    private val _product = MutableLiveData<Product>()

    val product: LiveData<Product>
        get() = _product

    private var _navigateOverviewFragment = MutableLiveData<Boolean?>()

    val navigateOverviewFragment: MutableLiveData<Boolean?>
        get() = _navigateOverviewFragment

    init {
        _product.value = product
    }


    fun deleteProduct(view : View) {
        SpaceOneApi.retrofitService.deleteProduct(product.value?.id.toString()).enqueue(object : Callback<ResponseBody> {
            override fun onResponse(
                call: Call<ResponseBody>,
                response: Response<ResponseBody>
            ) {
                _navigateOverviewFragment.value = true
                Log.d("OverviewViewModel", "Success")
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.d("OverviewViewModel", t.message.toString())
            }

        })
    }

    fun navigationDone() {
        _navigateOverviewFragment.value = null
    }

}