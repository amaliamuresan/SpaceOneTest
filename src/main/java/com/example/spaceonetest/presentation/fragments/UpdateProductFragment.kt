package com.example.spaceonetest.presentation.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.example.spaceonetest.databinding.FragmentUpdateProductBinding
import com.example.spaceonetest.models.Product
import com.example.spaceonetest.presentation.viewmodels.OverviewViewModel
import com.example.spaceonetest.service.SpaceOneApi
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class UpdateProductFragment : Fragment() {

    lateinit var product: Product

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        product = UpdateProductFragmentArgs.fromBundle(requireArguments()).product

        val binding = FragmentUpdateProductBinding.inflate(inflater)

        binding.lifecycleOwner = this

        binding.product = product

        binding.nameEditText.addTextChangedListener { text ->
            product.name = text.toString()
        }

        binding.weightEditText.addTextChangedListener { text ->
            product.weight = text.toString().toDouble()
            Log.d("UpdateProductFragment", "Weight changed")
        }

        binding.categoryEditText.addTextChangedListener { text ->
            product.category = text.toString()
        }

        binding.saveButton.setOnClickListener {
            updateProduct()
        }

        return binding.root
    }

    private fun updateProduct() {
        SpaceOneApi.retrofitService.updateItem(product.id, product).enqueue(object :
            Callback<ResponseBody> {
            override fun onResponse(
                call: Call<ResponseBody>,
                response: Response<ResponseBody>
            ) {
                findNavController().navigate(UpdateProductFragmentDirections.actionUpdateProductFragmentToOverviewFragment())

            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.d("UpdateProductFragment", t.message.toString())
            }
        })
    }
}