package com.example.spaceonetest

import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.spaceonetest.models.Product
import com.example.spaceonetest.presentation.ProductAdapter

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data : LiveData<List<Product>>?) {
    val adapter = recyclerView.adapter as ProductAdapter
    if(data != null) {
        adapter.submitList(data.value)
    }
}