package com.example.spaceonetest.presentation.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.example.spaceonetest.databinding.FragmentAddNewProductBinding
import com.example.spaceonetest.getCategoryIdByName
import com.example.spaceonetest.models.ProductDTO
import com.example.spaceonetest.presentation.viewmodels.OverviewViewModel
import com.example.spaceonetest.service.SpaceOneApi
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddNewProductFragment : Fragment() {

    private var product = ProductDTO()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val categories: List<String>? = OverviewViewModel.categories.value?.map {
            it.name
        }

        val binding = FragmentAddNewProductBinding.inflate(inflater)

        binding.nameEditText.addTextChangedListener { text ->
            product.name = text.toString()
        }

        binding.weightEditText.addTextChangedListener { text ->
            product.weight = text.toString().toDouble()
        }

        binding.addButton.setOnClickListener {
            addProduct(product)
        }

        val adapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, categories!!)
        binding.categoriesSpinner.adapter = adapter

        binding.categoriesSpinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            ) {
                val name = parent.getItemAtPosition(position).toString()
                product.category = getCategoryIdByName(name)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }

        return binding.root
    }

    private fun addProduct(productDto: ProductDTO) {
        SpaceOneApi.retrofitService.postProduct(productDto).enqueue(object :
            Callback<ResponseBody> {
            override fun onResponse(
                call: Call<ResponseBody>,
                response: Response<ResponseBody>
            ) {

                Log.d("AddNewProductFragment - Success", response.message())
                findNavController().navigate(AddNewProductFragmentDirections.actionAddNewProductFragmentToOverviewFragment())

            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.d("AddNewProductFragment", t.message.toString())
            }
        })
    }

}